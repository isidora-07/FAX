using Autofac.Core;
using Google.Apis.Auth.OAuth2;
using Google.Apis.Download;
using Google.Apis.Drive.v3;
using Google.Apis.Services;
using GoogleDriveASPNetCore.Models;
using Microsoft.Graph;
using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography;
using System.Threading;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.Helpers
{
    public class GoogleDriveApiHelper : IGoogleDriveApiHelper
    {
        private static DriveService driveService;
        public DriveService GetDriveService()
        {
            if(driveService == null)
            {
                return GetDriveServiceSingleton();
            }
            return driveService;
        }
        public DriveService GetDriveServiceSingleton()
        {
            string[] scope = { DriveService.Scope.Drive};
            UserCredential credentials;

            using (FileStream stream = new FileStream(Path.Combine("client_secret_850134855250-abc8nbua86186psrmsamv238p8dfmenc.apps.googleusercontent.com.json"), FileMode.Open, FileAccess.Read))
            {
                ClientSecrets secrets = GoogleClientSecrets.Load(stream).Secrets;

                credentials = GoogleWebAuthorizationBroker.AuthorizeAsync(secrets, scope, "isixaran7@gmail.com", CancellationToken.None, null).Result;
            }

            driveService = new DriveService(new BaseClientService.Initializer()
            {
                HttpClientInitializer = credentials,
                ApplicationName = "GoogleDriveASPNetCore"
            });

            return driveService;
        }

        public IList<Google.Apis.Drive.v3.Data.File> GetFiles()
        {
            FilesResource.ListRequest listRequest = GetDriveService().Files.List();
            listRequest.PageSize = 10;
            listRequest.Fields = "nextPageToken, files(id, name, mimeType)";

            IList<Google.Apis.Drive.v3.Data.File> files = listRequest.Execute().Files.ToList();
            List<FileModel> newFiles = new List<FileModel>();

            foreach(IList<Google.Apis.Drive.v3.Data.File> file in files)
            {
                newFiles.Add(new FileModel()
                {

                });
            }

            return files;
        }

        public string GetFileName(string id)
        {
            //Google.Apis.Drive.v3.Data.File file = Google.Apis.Drive.v3.Data.File();
            //file.Id = id;
            FilesResource.GetRequest getRequest = GetDriveService().Files.Get(id);

            return getRequest.Execute().Name;
        }

        public void DeleteFile(string id)
        {
            FilesResource.DeleteRequest deleteRequest = GetDriveService().Files.Delete(id);
            deleteRequest.Execute();
        }

        public FileModel UploadFile(string path) 
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            file.Name = Path.GetFileName(path);
            file.MimeType = MimeKit.MimeTypes.GetMimeType(file.Name);

            FilesResource.CreateMediaUpload createMediaUpload;

            using (FileStream stream = new FileStream(path, FileMode.Open))
            {
                createMediaUpload = GetDriveService().Files.Create(file, stream, file.MimeType);
                createMediaUpload.Fields = "id, name, mimeType, parents";
                createMediaUpload.Upload();
            }

            FileModel fileModel = new FileModel()
            {
                id = createMediaUpload.ResponseBody.Id,
                name = createMediaUpload.ResponseBody.Name,
                MimeType = createMediaUpload.ResponseBody.MimeType,
                drivePath = createMediaUpload.ResponseBody.Parents != null ? GetFileName(createMediaUpload.ResponseBody.Parents.ElementAt(0)) : null
            };

            return fileModel;

        }

        public void DownloadFile(string id, string savePath)
        {
            FilesResource.GetRequest getRequest = GetDriveService().Files.Get(id);
            MemoryStream stream = new MemoryStream();
            getRequest.MediaDownloader.ProgressChanged += (Google.Apis.Download.IDownloadProgress downloadProgress) =>
            {
                switch (downloadProgress.Status)
                {
                    case Google.Apis.Download.DownloadStatus.Completed:
                        {
                            using (FileStream fileStream = new FileStream(savePath, FileMode.Create, FileAccess.Write))
                            {
                                stream.WriteTo(fileStream);
                                break;
                            }
                        }
                }
            };
            getRequest.Download(stream);
        }

        private string GetMimeType(string fileName)
        {
            string mimeType = "application/unknown";
            string ext = System.IO.Path.GetExtension(fileName).ToLower();
            Microsoft.Win32.RegistryKey regKey = Microsoft.Win32.Registry.ClassesRoot.OpenSubKey(ext);
            if (regKey != null && regKey.GetValue("Content Type") != null)
                mimeType = regKey.GetValue("Content Type").ToString();
            return mimeType;
        }

        public string UploadFile2(string path, string mimeType, string parentId)
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            file.Name = Path.GetFileName(path);
            file.MimeType = mimeType;
            file.Parents = new List<string>
            {
                parentId
            };

            FilesResource.CreateMediaUpload createMedia;
            using (FileStream stream = new FileStream(path, FileMode.Open))
            {
                createMedia = GetDriveService().Files.Create(file, stream, file.MimeType);
                createMedia.Fields = "id";
                createMedia.Upload();
            }
            return createMedia.ResponseBody.Id;
        }

        public string CreateFolder(string path)
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            string[] directories = path.Split(Path.DirectorySeparatorChar);
            file.Name = directories[directories.Length - 1];
            file.MimeType = "application/vnd.google-apps.folder";
          
            FilesResource.CreateRequest request;

            request = GetDriveService().Files.Create(file);
            request.Fields = "id";
            return request.Execute().Id;
        }

        public string UproadFolder(string path)
        {
            string[] filePaths = System.IO.Directory.GetFiles(path);
            string parentId = this.CreateFolder(path);

            foreach (var filePath in filePaths)
            {
                string mimeType = GetMimeType(Path.GetFileName(filePath));
                UploadFile2(filePath, mimeType, parentId);
            }

            return parentId;
        }

        public string ShereFile(string id, string user)
        {
            Google.Apis.Drive.v3.Data.Permission permission = new Google.Apis.Drive.v3.Data.Permission()
            {
                EmailAddress = user,
                Role = "reader", // sta moze da radi
                Type = "user" // grupa ili korisnik
            };

            PermissionsResource.CreateRequest createRequest = GetDriveService().Permissions.Create(permission, id);

            return createRequest.Execute().Id;
        }
    }
}

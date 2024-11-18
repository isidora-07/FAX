using Google.Apis.Auth.OAuth2;
using Google.Apis.Drive.v3;
using Google.Apis.Drive.v3.Data;
using Google.Apis.Services;
using loboASPNET.BLL.Interfaces;
using loboASPNET.Models.GoogleDrive;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;

namespace loboASPNET.Helper
{
    public class GoogleDriveApiHelper : IGoogleDriveApiHelper
    {
        private static DriveService service;
        private readonly IGoogleDriveBLL _googleDriveBLL;

        public GoogleDriveApiHelper(IGoogleDriveBLL googleDriveBLL)
        {
            _googleDriveBLL = googleDriveBLL;
        }

        public DriveService GetDriveService()
        {
            if (service == null)
            {
                return GetDriveServiceSingleton();
            }
            return service;
        }
        public void DeleteFile(string id)
        {
            FilesResource.DeleteRequest deleteRequest = GetDriveService().Files.Delete(id);
            deleteRequest.Execute();
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

        public DriveService GetDriveServiceSingleton()
        {
            string[] scope = { DriveService.Scope.Drive };
            UserCredential credential;

            using (FileStream stream = new FileStream(Path.Combine("client_secret.json"), FileMode.Open, FileAccess.Read))
            {
                ClientSecrets secrets = GoogleClientSecrets.Load(stream).Secrets;

                credential = GoogleWebAuthorizationBroker.AuthorizeAsync(
                    secrets, scope, "isixaran7@gmail.com", CancellationToken.None, null).Result;
            }

            // kreiramo api servis
            service = new DriveService(new BaseClientService.Initializer()
            {
                HttpClientInitializer = credential,
                ApplicationName = "loboASPNET",
            });

            return service;
        }

        public string GetFileName(string id)
        {
            FilesResource.GetRequest getRequest = GetDriveService().Files.Get(id);
            return getRequest.Execute().Name;
        }

        public IList<FileModel> GetFiles()
        {
            FilesResource.ListRequest request = GetDriveService().Files.List();
            request.PageSize = 10;
            request.Fields = "nextPageToken, files(id, name, mimeType)";
            IList<Google.Apis.Drive.v3.Data.File> files = request.Execute().Files.ToList();

            List<FileModel> newFiles = new List<FileModel>();

            foreach (var item in files)
            {
                newFiles.Add(new FileModel()
                {
                    Id = item.Id,
                    Name = item.Name
                });
            }

            return newFiles;
        }

        /*
        public string UploadFile( string path)
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            file.Name = Path.GetFileName(path);

            file.MimeType = "application/pdf";

            FilesResource.CreateMediaUpload createMedia;

            using(FileStream stream = new FileStream(path, FileMode.Open))
            {
                createMedia = GetDriveService().Files.Create(file, stream, file.MimeType);
                createMedia.Fields = "id";
                createMedia.Upload();
            }
            return createMedia.ResponseBody.Id;
        }
        */
        public FileModel UploadFile(string path)
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            file.Name = Path.GetFileName(path);

            file.MimeType = MimeKit.MimeTypes.GetMimeType(file.Name);

            FilesResource.CreateMediaUpload createMedia;

            using (FileStream stream = new FileStream(path, FileMode.Open))
            {
                createMedia = GetDriveService().Files.Create(file, stream, file.MimeType);
                createMedia.Fields = "id, name, mimeType, parents";
                createMedia.Upload();
            }
            FileModel fileModel = new FileModel()
            {
                Id = createMedia.ResponseBody.Id,
                Name = createMedia.ResponseBody.Name,
                MimeType = createMedia.ResponseBody.Id,
                DrivePath = createMedia.ResponseBody.Parents != null ? GetFileName(createMedia.ResponseBody.Parents.ElementAt(0)) : null
            };

            return fileModel;
        }

        public string UploadAnyFile(string path, string mimeType, string parentId)
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

        public string CreateFolder(string path, string parId = null)
        {
            Google.Apis.Drive.v3.Data.File file = new Google.Apis.Drive.v3.Data.File();
            string[] directories = path.Split(Path.DirectorySeparatorChar);
            file.Name = directories[directories.Length - 1];
            file.MimeType = "application/vnd.google-apps.folder";
            if (parId != null)
            {
                file.Parents = new List<string>
                {
                    parId
                };
            }

            FilesResource.CreateRequest request;

            request = GetDriveService().Files.Create(file);
            request.Fields = "id";

            return request.Execute().Id;
        }

        public string UploadFolderWithFiles(string path, string parid = null)
        {
            string[] filePaths = Directory.GetFiles(path);
            string parentId = this.CreateFolder(path, parid);

            foreach (var item in filePaths)
            {
                string mimeType = GetMimeType(Path.GetFileName(item));
                UploadAnyFile(item, mimeType, parentId);
            }

            string[] dirs = Directory.GetDirectories(path);
            foreach (var item in dirs)
            {
                this.UploadFolderWithFiles(item, parentId);
            }

            return parentId;
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

        public string ShareFile(string id, string user)
        {
            Permission permission = new Permission()
            {
                EmailAddress = user,
                Role = "reader",
                Type = "user"
            };

            PermissionsResource.CreateRequest createRequest = GetDriveService().Permissions.Create(permission, id);
            return createRequest.Execute().Id;
        }

        public List<DocumentModel> DovnloadOneType(int typeId, string savePath)
        {
            List<DocumentModel> documents = _googleDriveBLL.GetDocumentByType(typeId);

            if (documents != null && documents.Count() > 0)
            {
                foreach (var doc in documents)
                {

                    this.DownloadFile(doc.DriveDocumentId, savePath + doc.Name);

                }
            }
            return documents;
        }
    }
}

using Google.Apis.Drive.v3;
using Google.Apis.Drive.v3.Data;
using GoogleDriveASPNetCore.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.Helpers
{
    public interface IGoogleDriveApiHelper
    {
        //public DriveService GetDriveService();
        public IList<Google.Apis.Drive.v3.Data.File> GetFiles();
        public string GetFileName(string id);
        public void DeleteFile(string id);
        public FileModel UploadFile(string path);
        public void DownloadFile(string id, string savePath);
        public string UploadFile2(string path, string mimeType, string parentId);
        public string CreateFolder(string path);
        public string UproadFolder(string path);
        public string ShereFile(string id, string user);
    }
}

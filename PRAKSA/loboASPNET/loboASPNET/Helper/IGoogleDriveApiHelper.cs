using loboASPNET.Models.GoogleDrive;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.Helper
{
    public interface IGoogleDriveApiHelper
    {
        public IList<FileModel> GetFiles();
        public string GetFileName(string id);
        public void DeleteFile(string id);
        public FileModel UploadFile(string path);
        public void DownloadFile(string id, string savePath);
        public string CreateFolder(string path, string parid = null);
        public string UploadAnyFile(string path, string mimeType, string parentId);
        public string UploadFolderWithFiles(string path, string parid = null);
        public string ShareFile(string id, string user);
        public List<DocumentModel> DovnloadOneType(int typeId, string savePath);
    }
}

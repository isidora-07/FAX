using GoogleDriveASPNetCore.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.BLL.Interface
{
    public interface IGoogleDriveBLL
    {
        public void CreateDocument(DocumentModel documentModel);
        public string GetDocumentData(string driveId);
        public void CreateShere(string shereId, string fileId, string user);
        public List<DocumentModel> GetDocumentsByTypeId(int typeId);
        public int GetTypeId(string typeName);
    }
}

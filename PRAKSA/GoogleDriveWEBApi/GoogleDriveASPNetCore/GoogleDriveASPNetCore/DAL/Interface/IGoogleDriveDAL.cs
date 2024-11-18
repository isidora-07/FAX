using GoogleDriveASPNetCore.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection.Metadata;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.DAL.Interface
{
    public interface IGoogleDriveDAL
    {
        public void CreateDocument(DocumentModel documentModel);
        public string GetDocumentData(string driveId);
        public void CreateShere(string shereId, string fileId, string user);
        public List<DocumentModel> GetDocumentsByTypeId(int typeId);
        public int GetTypeId(string typeName);
    }
}

using loboASPNET.Models.GoogleDrive;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.DAL.Interfaces
{
    public interface IGoogleDriveDAL
    {
        public void CreateDocument(DocumentModel documentModel);
        public string GetDocumentData(string driveId);
        public void CreateShare(string shareId, string id, string user);
        public List<DocumentModel> GetDocumentByType(int typeId);
    }
}

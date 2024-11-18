using loboASPNET.BLL.Interfaces;
using loboASPNET.DAL.Interfaces;
using loboASPNET.Models.GoogleDrive;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class GoogleDriveBLL : IGoogleDriveBLL
    {
        private readonly IGoogleDriveDAL _googleDriveDAL;

        public GoogleDriveBLL(IGoogleDriveDAL googleDriveDAL)
        {
            _googleDriveDAL = googleDriveDAL;
        }

        public void CreateDocument(DocumentModel documentModel)
        {
            _googleDriveDAL.CreateDocument(documentModel);
        }

        public void CreateShare(string shareId, string id, string user)
        {
            _googleDriveDAL.CreateShare(shareId, id, user);
        }

        public List<DocumentModel> GetDocumentByType(int typeId)
        {
            return _googleDriveDAL.GetDocumentByType(typeId);
        }

        public string GetDocumentData(string driveId)
        {
            return _googleDriveDAL.GetDocumentData(driveId);
        }
    }
}

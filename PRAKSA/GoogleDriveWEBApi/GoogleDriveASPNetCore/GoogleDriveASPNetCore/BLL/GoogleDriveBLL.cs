using Google.Apis.Drive.v3.Data;
using GoogleDriveASPNetCore.BLL.Interface;
using GoogleDriveASPNetCore.DAL.Interface;
using GoogleDriveASPNetCore.Models;
using Microsoft.Graph;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.BLL
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

        public void CreateShere(string shereId, string fileId, string user)
        {
            _googleDriveDAL.CreateShere(shereId, fileId, user);
        }

        public string GetDocumentData(string driveId)
        {
           return  _googleDriveDAL.GetDocumentData(driveId);
        }

        public List<DocumentModel> GetDocumentsByTypeId(int typeId)
        {
            return _googleDriveDAL.GetDocumentsByTypeId(typeId);
        }

        public int GetTypeId(string typeName)
        {
            return _googleDriveDAL.GetTypeId(typeName);
        }
    }
}

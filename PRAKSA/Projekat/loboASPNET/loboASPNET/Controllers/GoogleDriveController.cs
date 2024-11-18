using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using loboASPNET.BLL.Interfaces;
using loboASPNET.Helper;
using loboASPNET.Models;
using loboASPNET.Models.GoogleDrive;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DocumentModel = loboASPNET.Models.GoogleDrive.DocumentModel;

namespace loboASPNET.Controllers
{
    [Route("api/googledrive")]
    [ApiController]
    public class GoogleDriveController : ControllerBase
    {
        private readonly IGoogleDriveApiHelper _googleDriveApiHelper;
        private readonly IGoogleDriveBLL _googleDriveBLL;

        public GoogleDriveController(IGoogleDriveApiHelper googleDrive, IGoogleDriveBLL googleDriveBLL)
        {
            _googleDriveApiHelper = googleDrive;
            _googleDriveBLL = googleDriveBLL;
        }

        [HttpGet]
        [Route("listFiles")]
        public IList<FileModel> ListFile()
        {
            return _googleDriveApiHelper.GetFiles();
        }

        [HttpGet]
        [Route("getFileName")]
        public string GetFileName(string id)
        {
            return _googleDriveApiHelper.GetFileName(id);
        }

        [HttpDelete]
        [Route("delete")]
        public void Delete(string id)
        {
            _googleDriveApiHelper.DeleteFile(id);
        }
        
        [HttpGet]
        [Route("downloadFile")]
        public string DownloadFile(string driveId, string savePath)
        {
            _googleDriveApiHelper.DownloadFile(driveId, savePath);

            return _googleDriveBLL.GetDocumentData(driveId);
        }


        [HttpGet]
        [Route("uploadFolder")]
        public void UploadFolder(string path, int typeID)
        {
            _googleDriveApiHelper.UploadFolderWithFiles(path);
        }


        [HttpPost]
        [Route("uploadfile")]
        public string UploadFile(string path, int typeId)
        {
            FileModel file = _googleDriveApiHelper.UploadFile(path);

            _googleDriveBLL.CreateDocument(new DocumentModel()
            {
                Name = file.Name,
                DriveDocumentId = file.Id,
                DrivePath = file.DrivePath,
                Type = new TypeModel()
                {
                    Id = typeId
                }
            });
            return file.Id;
        }

        [HttpPost]
        [Route("shareFile")]
        public string ShareFile(string id, string user)
        {
            string id1 = _googleDriveApiHelper.ShareFile(id, user);
            _googleDriveBLL.CreateShare(id1, id, user);
            return id1;
        }

        [HttpGet]
        [Route("downloadFileByType")]
        public List<DocumentModel> DownloadFileByType(int typeId, string savePath)
        {
            return _googleDriveApiHelper.DovnloadOneType(typeId, savePath);

        }
    }
}

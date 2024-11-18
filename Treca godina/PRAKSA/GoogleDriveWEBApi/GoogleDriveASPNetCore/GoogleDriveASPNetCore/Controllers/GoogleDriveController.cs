using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using Google.Apis.Drive.v3;
using GoogleDriveASPNetCore.BLL.Interface;
using GoogleDriveASPNetCore.Helpers;
using GoogleDriveASPNetCore.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace GoogleDriveASPNetCore.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class GoogleDriveController : ControllerBase
    {
        private readonly IGoogleDriveApiHelper _googleDriveApiHelper;
        private readonly IGoogleDriveBLL _googleDriveBLL;
        public GoogleDriveController(IGoogleDriveApiHelper googleDriveApiHelper, IGoogleDriveBLL googleDriveBLL)
        {
            _googleDriveApiHelper = googleDriveApiHelper;
            _googleDriveBLL = googleDriveBLL;
        }

        [HttpGet]
        [Route("/authorize")]
        public IActionResult Init()
        {
            return Ok();        }

        [HttpGet]
        [Route("listFiles")]
        public IList<Google.Apis.Drive.v3.Data.File> ListFiles()
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
        [Route("deleteFile")]
        public void DeleteFile(string id)
        {
            _googleDriveApiHelper.DeleteFile(id);
        }

        /* [HttpPost]
         [Route("uploadFile")]
         public string UploadFile(string path)
         {
             return _googleDriveApiHelper.UploadFile(path);
         }*/

        [HttpPost]
        [Route("uploadFile")]
        public string UploadFile(string path, int typeId)
        {
            FileModel file = _googleDriveApiHelper.UploadFile(path);

           _googleDriveBLL.CreateDocument(new Models.DocumentModel()
            {
                name = file.name,
                driveDocumentId = file.id,
                drivePath = file.drivePath,
                type = new TypeModel()
                {
                    id = typeId
                }

            });

            return file.id;
        }

        [HttpGet]
        [Route("downloadFile")]
        public string DownloadFile(string id, string savePath)
        {
            _googleDriveApiHelper.DownloadFile(id, savePath);

            return _googleDriveBLL.GetDocumentData(id); 

        }

        [HttpGet]
        [Route("uploadFolder")]
        public void UploadFolder(string path)
        {
            _googleDriveApiHelper.UproadFolder(path);
        }

        [HttpPost]
        [Route("shereFile")]
        public string ShereFile(string id, string user)
        {
            string shereId = _googleDriveApiHelper.ShereFile(id, user);

            _googleDriveBLL.CreateShere(shereId, id, user);

            return shereId;
        }

        [HttpGet]
        [Route("downloadFilesWithType")]
        public void DownloadFilesWithType(string typeName)
        {
            int typeId = _googleDriveBLL.GetTypeId(typeName);

            List<DocumentModel> doc = _googleDriveBLL.GetDocumentsByTypeId(typeId);

            foreach (DocumentModel document in doc)
            {
                _googleDriveApiHelper.DownloadFile(document.driveDocumentId, "C:\\Users\\Isidora\\Desktop\\FAX\\3.godina\\PRAKSA\\proba" + document.name);
            }
        }
    }
}

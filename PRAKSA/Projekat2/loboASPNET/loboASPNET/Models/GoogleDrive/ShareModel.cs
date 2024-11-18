using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.Models.GoogleDrive
{
    public class ShareModel
    {
        public int Id { get; set; }
        public string DriveShareId { get; set; }
        public UserModel User { get; set; }
        public DocumentModel Document { get; set; }
    }
}

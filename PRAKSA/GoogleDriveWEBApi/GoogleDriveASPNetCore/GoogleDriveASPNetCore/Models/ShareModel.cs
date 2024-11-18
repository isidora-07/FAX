using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.Models
{
    public class ShareModel
    {
        public int id { get; set; }
        public string driveShareId { get; set; }
        public UserModel User { get; set; }
        public DocumentModel Document { get; set; }

    }
}

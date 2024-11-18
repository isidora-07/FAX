using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.Models
{
    public class FileModel
    {
        public string id { get; set; }
        public string name { get; set; }
        public string MimeType { get; set; }
        public string drivePath { get; set; }
    }
}

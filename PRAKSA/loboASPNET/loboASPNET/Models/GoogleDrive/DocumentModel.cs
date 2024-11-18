using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.Models.GoogleDrive
{
    public class DocumentModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string DrivePath { get; set; }
        public DateTime CreationDate { get; set; }
        public string DriveDocumentId { get; set; }
        public TypeModel Type { get; set; }
    }
}

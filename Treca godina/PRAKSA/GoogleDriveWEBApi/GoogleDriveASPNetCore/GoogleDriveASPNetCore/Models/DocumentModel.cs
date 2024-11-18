using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace GoogleDriveASPNetCore.Models
{
    public class DocumentModel
    {
        public int id { get; set; }
        public string name { get; set; }
        public string drivePath { get; set; }
	    public DateTime creationDate { get; set; }
	    public string driveDocumentId { get; set; }
	    public TypeModel type { get; set; }
    }
}

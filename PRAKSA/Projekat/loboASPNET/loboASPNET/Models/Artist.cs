using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.Models
{
    public class Artist
    {
        public int idArtist { get; set; }
        public string nameArtist { get; set; }
        public DateTime dateArtist { get; set; }
        public int idBend { get; set; }
    }
}

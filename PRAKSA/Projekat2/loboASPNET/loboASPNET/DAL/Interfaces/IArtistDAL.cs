using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public interface IArtistDAL
    {
        public List<dynamic> Select();
        public bool Insert(Artist artist);
        public List<dynamic> GetArtistByID(int id);
        public bool DeleteArtistByID(int id);
        public bool UpdateArtist(int idArtist, string nameArtist, string dateArtist);
    }
}

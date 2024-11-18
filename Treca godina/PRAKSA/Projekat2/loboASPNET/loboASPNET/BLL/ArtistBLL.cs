using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class ArtistBLL : IArtistBLL
    {
        private readonly IArtistDAL _artistDAL;

        public ArtistBLL(IArtistDAL artistDAL)
        {
            _artistDAL = artistDAL;
        }

        public bool DeleteArtistByID(int id)
        {
            return _artistDAL.DeleteArtistByID(id);
        }

        public List<dynamic> GetArtistByID(int id)
        {
            return _artistDAL.GetArtistByID(id);
        }

        public bool Insert(Artist artist)
        {
            return _artistDAL.Insert(artist);
        }

        public List<dynamic> Select()
        {
            return _artistDAL.Select();
        }

        public bool UpdateArtist(int idArtist, string nameArtist, string dateArtist)
        {
            return _artistDAL.UpdateArtist(idArtist,nameArtist,dateArtist);
        }
    }
}

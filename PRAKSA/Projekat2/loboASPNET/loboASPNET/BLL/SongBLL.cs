using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class SongBLL : ISongBLL
    {
        private readonly ISongDAL _songDAL;

        public SongBLL(ISongDAL songDAL)
        {
            _songDAL = songDAL;
        }

        public bool DeleteSongByID(int id)
        {
            return _songDAL.DeleteSongByID(id);
        }

        public List<dynamic> GetSongByID(int id)
        {
            return _songDAL.GetSongByID(id);
        }

        public bool Insert(Song song)
        {
            return _songDAL.Insert(song);
        }

        public List<dynamic> Select()
        {
            return _songDAL.Select();
        }

        public bool UpdateSong(int idSong, string nameSong)
        {
            return _songDAL.UpdateSong(idSong, nameSong);
        }
    }
}

using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public interface ISongDAL
    {
        public List<dynamic> Select();
        public bool Insert(Song song);
        public List<dynamic> GetSongByID(int id);
        public bool DeleteSongByID(int id);
        public bool UpdateSong(int idSong, string nameSong);
    }
}

using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public interface IBendDAL
    {
        public List<dynamic> Select();
        public bool Insert(Bend bend);
        public List<dynamic> GetBendByID(int id);
        public bool DeleteBendByID(int id);
        public List<dynamic> GetArtistByIdBend(int idBend);
    }
}

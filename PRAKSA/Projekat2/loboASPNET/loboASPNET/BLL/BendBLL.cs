using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class BendBLL : IBendBLL
    {
        private readonly IBendDAL _bendDAL;

        public BendBLL(IBendDAL bendDAL)
        {
            _bendDAL = bendDAL;
        }

        public bool DeleteBendByID(int id)
        {
            return _bendDAL.DeleteBendByID(id);
        }

        public List<dynamic> GetArtistByIdBend(int idBend)
        {
            return _bendDAL.GetArtistByIdBend(idBend);
        }

        public List<dynamic> GetBendByID(int id)
        {
            return _bendDAL.GetBendByID(id);
        }

        public bool Insert(Bend bend)
        {
            return _bendDAL.Insert(bend);
        }

        public List<dynamic> Select()
        {
            return _bendDAL.Select();
        }
    }
}

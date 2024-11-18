using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public interface IGenreDAL
    {
        public List<Genre> Select();
        public bool Insert(Genre genre);
        public Genre GetGenreByID(int id);
        public bool DeleteGenreByID(int id);
        public bool UpdateGenre(int idGenre, string nameGenre);
    }
}

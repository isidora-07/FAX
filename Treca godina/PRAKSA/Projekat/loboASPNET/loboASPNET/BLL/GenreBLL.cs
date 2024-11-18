using loboASPNET.Models;
using System;
using System.Collections.Generic;
using System.Data.Common;
using System.Linq;
using System.Threading.Tasks;

namespace loboASPNET.BLL
{
    public class GenreBLL : IGenreBLL
    {
        private readonly IGenreDAL _genreDAL;

        public GenreBLL(IGenreDAL genreDAL)
        {
            _genreDAL = genreDAL;
        }

        public bool DeleteGenreByID(int id)
        {
            return _genreDAL.DeleteGenreByID(id);
        }

        public Genre GetGenreByID(int id)
        {
            return _genreDAL.GetGenreByID(id);
        }

        public bool Insert(Genre genre)
        {
            return _genreDAL.Insert(genre);
        }

        public List<Genre> Select()
        {
            return _genreDAL.Select();
        }

        public bool UpdateGenre(int idGenre, string nameGenre)
        {
            return _genreDAL.UpdateGenre(idGenre, nameGenre);
        }
    }
}

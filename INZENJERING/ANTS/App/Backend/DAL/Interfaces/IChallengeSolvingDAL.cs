﻿using Backend.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Backend.DAL.Interfaces
{
    public interface IChallengeSolvingDAL
    {
        List<ChallengeSolving> getAllSolutionsForPost(long postId);
        ChallengeSolving insertSolution(ChallengeSolving challenge);
        ChallengeSolving solutionFromTheInstitution(ChallengeSolving challenge);
        List<ChallengeSolving> solvingChallenge(long solutionId, long postId);
        bool deleteSolution(long id);
        ChallengeSolving editSolution(long id, string description);
    }
}

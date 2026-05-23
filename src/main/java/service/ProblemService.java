package service;

import model.Problem;
import repository.ProblemRepository;

import java.util.ArrayList;

public class ProblemService {

    private ProblemRepository repository;

    public ProblemService() {

        repository = new ProblemRepository();
    }

    public ArrayList<Problem> getAllProblems() {

        return repository.getAllProblems();
    }

    public void addProblem(Problem problem) {

        if (!isValidDifficulty(problem.getDifficulty())) {

            System.out.println("Invalid Difficulty!");

            return;
        }

        repository.addProblem(problem);

        System.out.println("Problem Added Successfully!");
    }

    public Problem getProblemById(int id) {

        return repository.getProblemById(id);
    }

    public void updateProblemDifficulty(
            int id,
            String difficulty
    ) {

        if (!isValidDifficulty(difficulty)) {

            System.out.println("Invalid Difficulty!");

            return;
        }

        repository.updateProblemDifficulty(id, difficulty);
    }

    public void deleteProblem(int id) {

        repository.deleteProblem(id);
    }

    private boolean isValidDifficulty(String difficulty) {

        return difficulty.equalsIgnoreCase("Easy")
                || difficulty.equalsIgnoreCase("Medium")
                || difficulty.equalsIgnoreCase("Hard");
    }
}
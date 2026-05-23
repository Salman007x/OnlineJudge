package judge;

import utils.Verdict;

public class SimpleJudge {

    public static String judge(
            String code
    ) {

        code = code.toLowerCase();

        if (
                code.contains("for")
                        || code.contains("while")
        ) {

            return Verdict.ACCEPTED;
        }

        return Verdict.WRONG_ANSWER;
    }
}
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AcaPyService {

    private static final String ACA_PY_ENDPOINT = "http://localhost:8000/";
    private static final String PREFIX = "/bin/bash";
    private static final String C = "-c";

    public void start(String key) throws Exception{
        String terminalCommand = "aca-py provision \\\n" +
                "  --endpoint "+ ACA_PY_ENDPOINT +" \\\n" +
                "  --genesis-url http://dev.bcovrin.vonx.io/genesis \\\n" +
                "  --wallet-type indy \\\n" +
                "  --wallet-name AliceBCG1 \\\n" +
                "  --wallet-key "+ key +" \\\n" +
                "  --seed Suz1fbDsHQV9rjUTmVwA5JEkLg5Rbtgj/";

        ProcessBuilder pb1 = new ProcessBuilder(
                new String[] {PREFIX, C, terminalCommand});

        if (executeCommandAndGetOutput(pb1) != "") throw new RuntimeException("Provide error");

        terminalCommand = "aca-py start \\ \n" +
                "--label Alice \\\n" +
                "-it http 0.0.0.0 8000 \\\n" +
                "-ot http \\\n" +
                "--admin 0.0.0.0 11000 \\\n" +
                "--admin-insecure-mode \\\n" +
                "--genesis-url http://dev.bcovrin.vonx.io/genesis \\\n" +
                "--endpoint http://localhost:8000/ \\\n" +
                "--debug-connections \\\n" +
                "--auto-provision \\\n" +
                "--wallet-type indy \\\n" +
                "--wallet-name AliceBCG1 \\\n" +
                "--wallet-key "+ key +" \\\n" +
                "--seed Suz1fbDsHQV9rjUTmVwA5JEkLg5Rbtgj \\\n" +
                "--auto-accept-invites";

        ProcessBuilder pb2 = new ProcessBuilder(
                new String[] {PREFIX, C, terminalCommand});

        pb2.start();


    }

    // Calls to the proper ACA-PY endpoint to create invitation
    // and saves the result in the server
    public void createConnectionInvitation() throws IOException{
        String terminalCommand = "curl -X POST \"http://"+ ACA_PY_ENDPOINT +"/connections/create-invitation\" \\\n" +
                "   -H 'Content-Type: application/json' \\\n" +
                "   -d '{}'";

        ProcessBuilder pb = new ProcessBuilder(
                new String[] {PREFIX, C, terminalCommand});

        String invitation = executeCommandAndGetOutput(pb);

        try {
            JSONObject jsonObject = new JSONObject(invitation);
            //save that json into the server
            System.out.println(invitation);
        }catch (JSONException err){
            throw new IOException();
        }

    }

    // Calls to the proper ACA-PY endpoint to fetch the wallet
    // credentials and return the last one
    public String fetchWalletCredential() throws IOException{

        String terminalCommand = "curl -X GET \"http://"+ ACA_PY_ENDPOINT +"/credentials\" -H  \"accept: application/json\"";

        ProcessBuilder pb = new ProcessBuilder(
                new String[] {PREFIX, C, terminalCommand});

        return executeCommandAndGetOutput(pb);

    }

    private static String executeCommandAndGetOutput(ProcessBuilder pb) throws IOException
    {
        Process terminalCommandProcess = pb.start();
        InputStream inputStream = terminalCommandProcess.getInputStream();
        BufferedReader br = new BufferedReader(
                new InputStreamReader(inputStream));

        String output = "";
        String line;
        for (int i = 0;(line = br.readLine()) != null; i++)
        {
            output += line;
        }
        return output;
    }
}

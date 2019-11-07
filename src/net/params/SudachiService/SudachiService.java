package net.params.SudachiService;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import com.worksap.nlp.sudachi.*;

public class SudachiService implements Runnable
{
  boolean isRunning = false;
  private ServerSocket server;
  private String confFilePath;

  public SudachiService(String[] args)
  {
    int port = 4343;

    try
    {
      confFilePath = "../sudachi_fulldict.json";
      if (args.length > 0)
      {
        confFilePath = args[0];
      }

      if (args.length > 1)
      {
        port = Integer.parseInt(args[1]);
      }
      server = new ServerSocket();
      server.setReuseAddress(true);
      server.bind(new InetSocketAddress(port));
      System.out.printf("Using configuration file: %s\n", confFilePath);
      System.out.printf("Listening on port %d...\n", port);
    }
    catch (Exception ex)
    {
      System.out.println("Cannot create service.");
      System.out.println(ex.getMessage());
    }
  }

  public void stop()
  {
    try
    {
      isRunning = false;
      server.close();
    }
    catch (Exception ex)
    {
    }
  }

  static String readAll(InputStream input) throws IOException {
    try (InputStreamReader isReader = new InputStreamReader(input, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isReader)) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        return sb.toString();
    }
  }

  @Override
  public void run()
  {
    String settings = null;
    Tokenizer tokenizer = null;
    Tokenizer.SplitMode mode = Tokenizer.SplitMode.C;

    try (FileInputStream file = new FileInputStream(confFilePath))
    {
      settings = readAll(file);
      Dictionary dict = new DictionaryFactory().create(null, settings, false);
      tokenizer = dict.create();
    } catch (IOException ex)
    {
      System.out.println("Cannot read configuration file. ");
      System.out.println(ex.getMessage());
      return;
    }


    isRunning = true;
    while(isRunning)
    {
      try (Socket socket = server.accept())
      {
        InputStream inStream = socket.getInputStream();
        OutputStream outStream = socket.getOutputStream();

        try (InputStreamReader inputReader = new InputStreamReader(inStream, StandardCharsets.UTF_8);
          BufferedReader reader = new BufferedReader(inputReader);
          PrintStream output = new PrintStream(outStream, true, "UTF-8"))
        {
          for (String line = reader.readLine(); line != null; line = reader.readLine())
          {
            try
            {
                for (Morpheme m : tokenizer.tokenize(mode, line))
                {
                  output.print(m.readingForm());
                }
                output.println();
                output.flush();
            }
            catch (RuntimeException e)
            {
              output.println(e.getMessage());
            }
          }
        }
      }
      catch (IOException ex)
      {
        System.out.println("IO Exception.");
        System.out.println(ex.getMessage());
      }
    }
  }
}

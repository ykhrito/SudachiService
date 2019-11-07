package net.params.SudachiService;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;

public class SudachiServiceLauncher implements Daemon
{
  private static String[] startArgs;

  private static SudachiServiceLauncher launcher;

  private SudachiService service;

  public SudachiServiceLauncher()
  {
  }

  public void run()
  {
    service = new SudachiService(startArgs);

    try
    {
      Thread t = new Thread(service);
      t.setDaemon(true);
      t.start();
      t.join();
    }
    catch(InterruptedException e)
    {
    }
  }

  public void terminate()
  {
    if (service != null)
    {
      service.stop();
    }
  }

  public static void startService(String[] args)
  {
    startArgs = args;
    launcher = new SudachiServiceLauncher();
    launcher.run();
  }

  public static void stopService(String[] args)
  {
    if (launcher != null)
    {
      launcher.terminate();
    }
  }

  @Override
  public void init(DaemonContext ctx) throws DaemonInitException, Exception
  {
  }

  @Override
  public void start() throws Exception
  {
  }

  @Override
  public void stop() throws Exception
  {
  }

  @Override
  public void destroy()
  {
  }

  public static void main(String[] args)
  {
      startArgs = args;
      launcher = new SudachiServiceLauncher();
      launcher.run();
      return;
  }
}

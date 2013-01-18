package tk.newk.common;

import android.util.Log;

import static tk.newk.common.utils.*;

public class log
{
  static String m_tag = "";

  public static void log_set_tag(String tag)
  {
    m_tag = tag;
  }
  private static String make_log_str(String func_name, String ... info)
  {
    String log_info = "[" + str(now()) + "]" + func_name + ":";
    for (String s: info)
    {
      log_info += s + ",";
    }
    return log_info;
  }
  private static String make_method_name(Object o)
  {
    String s;
    if (o != null)
    {
      s = o.getClass().getName();
      String[] ss = s.split("\\.");
      s = ss[ss.length - 1];
    }
    else
    {
      s = new String();
    }
    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    int i;
    for (i = 0; i < ste.length; ++i)
    {
      if (ste[i].getMethodName().equals("make_method_name"))
        break;
    }
    i += 2;
    String method = "";
    if (i < ste.length)
    {
      method = ste[i].getMethodName();
    }
    if (i + 1 < ste.length)
    {
      method = ste[i + 1].getMethodName() + "->" + method;
    }
    s += "(" + method + ")";
    return s;
  }

  public static void logv(String func_name, String ... info)
  {
    Log.v(m_tag, make_log_str(func_name, info));
  }
  public static void logv(Object o, String ... info)
  {
    Log.v(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void logd(String func_name, String ... info)
  {
    Log.d(m_tag, make_log_str(func_name, info));
  }
  public static void logd(Object o, String ... info)
  {
    Log.d(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void logi(String func_name, String ... info)
  {
    Log.i(m_tag, make_log_str(func_name, info));
  }
  public static void logi(Object o, String ... info)
  {
    Log.i(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void logw(String func_name, String ... info)
  {
    Log.w(m_tag, make_log_str(func_name, info));
  }
  public static void logw(Object o, String ... info)
  {
    Log.w(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void loge(String func_name, String ... info)
  {
    Log.e(m_tag, make_log_str(func_name, info));
  }
  public static void loge(Object o, String ... info)
  {
    Log.e(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void logwtf(String func_name, String ... info)
  {
    Log.wtf(m_tag, make_log_str(func_name, info));
  }
  public static void logwtf(Object o, String ... info)
  {
    Log.wtf(m_tag, make_log_str(make_method_name(o), info));
  }
  public static void logexception(Object o, Throwable tr)
  {
    Log.w(m_tag, tr);
  }
}

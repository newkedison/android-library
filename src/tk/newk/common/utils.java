package tk.newk.common;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.sql.Time;

public class utils
{
  public static String str(int i) { return Integer.toString(i); }
  public static String str(boolean b) { return String.valueOf(b); }
  public static String str(float f) { return Float.toString(f); }
  public static String str(float f, String fmt)
  {
    DecimalFormat formatter = new DecimalFormat(fmt);
    return formatter.format(f);
  }
  public static String str(double d) { return Double.toString(d); }
  public static String str(double d, String fmt)
  {
    DecimalFormat formatter = new DecimalFormat(fmt);
    return formatter.format(d);
  }
  public static String str(long l) { return Long.toString(l); }
  public static String str(byte b) { return Integer.toHexString(b); }
  public static String str(Date d) 
  {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+0800"));
    return sdf.format(d);
  }
  //sample: str(new Date(), "yy-MM-dd HH:mm:ss")
  public static String str(Date d, String format)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    sdf.setTimeZone(TimeZone.getTimeZone("GMT+0800"));
    return sdf.format(d);
  }
  public static String format_ticket(long ticket, String fmt)
  {
    return str(new Time(ticket), fmt);
  }

  private static final char[] HEX_TABLE = 
    {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
     'A', 'B', 'C', 'D', 'E', 'F'};
  public static String str(byte[] byte_array)
  {
    char[] ret = new char[byte_array.length * 3];
    for (int i = 0; i < byte_array.length; ++i)
    {
      ret[i * 3] = HEX_TABLE[(0xF0 & byte_array[i]) >>> 4];
      ret[i * 3 + 1] = HEX_TABLE[0x0F & byte_array[i]];
      ret[i * 3 + 2] = ' ';
    }
    return "(" + str(byte_array.length) + ")"
      + "[" + new String(ret) + "]";
  }

  public static Date now() { return new Date(); }
  public static long ticket() { return new Date().getTime(); }

  public static Float[] bytes_to_floats(byte[] bytes, int start_pos,
      int float_count, boolean is_swapped)
  {
    int len = bytes.length;
    if (len < 4 || start_pos < 0 || len - start_pos < 4)
      return null;
    int tmp;
    ArrayList<Float> floats = new ArrayList<Float> ();
    int max_float_count = (len - start_pos) / 4;
    if (float_count > max_float_count || float_count <= 0)
      float_count = max_float_count;

    for (int i = 0; i < max_float_count; ++ i)
    {
      if (is_swapped)
      {
        tmp = (bytes[start_pos + 0 + 4 * i] & 0xFF) << 24
            | (bytes[start_pos + 1 + 4 * i] & 0xFF) << 16
            | (bytes[start_pos + 2 + 4 * i] & 0xFF) << 8
            | (bytes[start_pos + 3 + 4 * i] & 0xFF);
      }
      else
      {
        tmp = (bytes[start_pos + 2 + 4 * i] & 0xFF) << 24
            | (bytes[start_pos + 3 + 4 * i] & 0xFF) << 16
            | (bytes[start_pos + 0 + 4 * i] & 0xFF) << 8
            | (bytes[start_pos + 1 + 4 * i] & 0xFF);
      }
      floats.add(Float.intBitsToFloat(tmp));
    }
    return floats.toArray(new Float[floats.size()]);
  }
  private static final int[] table = {
    0x0000, 0xC0C1, 0xC181, 0x0140, 0xC301, 0x03C0, 0x0280, 0xC241,
    0xC601, 0x06C0, 0x0780, 0xC741, 0x0500, 0xC5C1, 0xC481, 0x0440,
    0xCC01, 0x0CC0, 0x0D80, 0xCD41, 0x0F00, 0xCFC1, 0xCE81, 0x0E40,
    0x0A00, 0xCAC1, 0xCB81, 0x0B40, 0xC901, 0x09C0, 0x0880, 0xC841,
    0xD801, 0x18C0, 0x1980, 0xD941, 0x1B00, 0xDBC1, 0xDA81, 0x1A40,
    0x1E00, 0xDEC1, 0xDF81, 0x1F40, 0xDD01, 0x1DC0, 0x1C80, 0xDC41,
    0x1400, 0xD4C1, 0xD581, 0x1540, 0xD701, 0x17C0, 0x1680, 0xD641,
    0xD201, 0x12C0, 0x1380, 0xD341, 0x1100, 0xD1C1, 0xD081, 0x1040,
    0xF001, 0x30C0, 0x3180, 0xF141, 0x3300, 0xF3C1, 0xF281, 0x3240,
    0x3600, 0xF6C1, 0xF781, 0x3740, 0xF501, 0x35C0, 0x3480, 0xF441,
    0x3C00, 0xFCC1, 0xFD81, 0x3D40, 0xFF01, 0x3FC0, 0x3E80, 0xFE41,
    0xFA01, 0x3AC0, 0x3B80, 0xFB41, 0x3900, 0xF9C1, 0xF881, 0x3840,
    0x2800, 0xE8C1, 0xE981, 0x2940, 0xEB01, 0x2BC0, 0x2A80, 0xEA41,
    0xEE01, 0x2EC0, 0x2F80, 0xEF41, 0x2D00, 0xEDC1, 0xEC81, 0x2C40,
    0xE401, 0x24C0, 0x2580, 0xE541, 0x2700, 0xE7C1, 0xE681, 0x2640,
    0x2200, 0xE2C1, 0xE381, 0x2340, 0xE101, 0x21C0, 0x2080, 0xE041,
    0xA001, 0x60C0, 0x6180, 0xA141, 0x6300, 0xA3C1, 0xA281, 0x6240,
    0x6600, 0xA6C1, 0xA781, 0x6740, 0xA501, 0x65C0, 0x6480, 0xA441,
    0x6C00, 0xACC1, 0xAD81, 0x6D40, 0xAF01, 0x6FC0, 0x6E80, 0xAE41,
    0xAA01, 0x6AC0, 0x6B80, 0xAB41, 0x6900, 0xA9C1, 0xA881, 0x6840,
    0x7800, 0xB8C1, 0xB981, 0x7940, 0xBB01, 0x7BC0, 0x7A80, 0xBA41,
    0xBE01, 0x7EC0, 0x7F80, 0xBF41, 0x7D00, 0xBDC1, 0xBC81, 0x7C40,
    0xB401, 0x74C0, 0x7580, 0xB541, 0x7700, 0xB7C1, 0xB681, 0x7640,
    0x7200, 0xB2C1, 0xB381, 0x7340, 0xB101, 0x71C0, 0x7080, 0xB041,
    0x5000, 0x90C1, 0x9181, 0x5140, 0x9301, 0x53C0, 0x5280, 0x9241,
    0x9601, 0x56C0, 0x5780, 0x9741, 0x5500, 0x95C1, 0x9481, 0x5440,
    0x9C01, 0x5CC0, 0x5D80, 0x9D41, 0x5F00, 0x9FC1, 0x9E81, 0x5E40,
    0x5A00, 0x9AC1, 0x9B81, 0x5B40, 0x9901, 0x59C0, 0x5880, 0x9841,
    0x8801, 0x48C0, 0x4980, 0x8941, 0x4B00, 0x8BC1, 0x8A81, 0x4A40,
    0x4E00, 0x8EC1, 0x8F81, 0x4F40, 0x8D01, 0x4DC0, 0x4C80, 0x8C41,
    0x4400, 0x84C1, 0x8581, 0x4540, 0x8701, 0x47C0, 0x4680, 0x8641,
    0x8201, 0x42C0, 0x4380, 0x8341, 0x4100, 0x81C1, 0x8081, 0x4040,
  };
  public static short crc16(byte[] data)
  {
    int crc = 0xFFFF;
    for (byte b : data) 
    {
      crc = (crc >>> 8) ^ table[(crc ^ b) & 0xff];
    }
    return (short)crc;
  }
  public static short crc16(byte[] data, int len)
  {
    if (len <= 0 || len > data.length)
      len = data.length;
    int crc = 0xFFFF;
    for (int i = 0; i < len; ++i) 
    {
      crc = (crc >>> 8) ^ table[(crc ^ data[i]) & 0xff];
    }
//    logd("crc16", "data", str(data), "len", str(len), "crc", str(crc));
    return (short)crc;
  }
  public static void add_crc(byte[] data)
  {
    short crc = crc16(data, data.length - 2);
//    logd("add_crc", str(data), str(crc));
    data[data.length - 2] = (byte)(crc & 0xFF);
    data[data.length - 1] = (byte)((crc >>> 8) & 0xFF);
//    logd("add_crc", str(data));
  }
  public static void assign_bytes(byte[] dst, int ... data)
  {
    int dst_len = dst.length;
    int copy_len = data.length;
    if (dst_len < copy_len)
      copy_len = dst_len;
    for (int i = 0; i < copy_len; ++i)
      dst[i] = (byte)(data[i] & 0xFF);
  }
}

// vim: fdm=syntax fdl=1 fdn=2

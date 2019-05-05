import java.net.*;
import java.io.*;
public class HTMLServer{
	public static void main(String[] args) {
		try{
			URL url = new URL("https://www.ctu.edu.vn");
			InputStream is = url.openStream();
			is = new BufferedInputStream(is);
			Reader reader = new InputStreamReader(is);
			int c;
			FileOutputStream fos = new FileOutputStream("result.html");
			while((c=reader.read()) != -1)
				fos.write(c);

		}catch(IOException ex){
			System.out.println(ex.toString());
		}
	}
}
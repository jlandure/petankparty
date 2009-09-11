/**
 * 
 */
package org.petank.server.rest.representation

import org.restlet.representation.StringRepresentation
import java.util.zip.GZIPOutputStream
import org.restlet.data.Encoding
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


/**
 * @author jlandure
 *
 */
public class GzipRepresentation extends StringRepresentation {

	public GzipRepresentation(CharSequence text, org.restlet.data.MediaType mediaType) {
		super(text, mediaType)
		this.setEncodings([Encoding.GZIP])
	}
	
	@Override
	 void write(OutputStream outputStream) throws IOException {
		 if (getText() != null) {
			GZIPOutputStream gos = null;
			
			gos = new GZIPOutputStream(outputStream);

            byte[] buf = getText().getBytes(getCharacterSet().getName())
			gos.write(buf, 0, buf.length)
			gos.finish();
		}
	}
	
}

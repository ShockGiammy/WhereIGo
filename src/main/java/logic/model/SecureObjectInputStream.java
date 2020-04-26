package logic.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import logic.controllers.ChatType;

public class SecureObjectInputStream extends ObjectInputStream {

	protected SecureObjectInputStream() throws IOException {
		super();
	}

	public SecureObjectInputStream(InputStream is) throws IOException {
		super(is);
	}

	@Override
	protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
		// Only deserialize instances of AllowedClass
		if ((!osc.getName().equals(PrivateMessage.class.getName())) && (!osc.getName().equals(ChatType.class.getName())) &&
		 (!osc.getName().equals(Enum.class.getName())) && (!osc.getName().equals(MessageType.class.getName()))) {
			throw new InvalidClassException("Unauthorized deserialization", osc.getName());
	    }
	    return super.resolveClass(osc);
	}
}
package oneapi.listener;

import java.util.EventListener;

public interface ResponseListener<T> extends EventListener {
	public void onGotResponse(T jsonObject, Throwable error);
}

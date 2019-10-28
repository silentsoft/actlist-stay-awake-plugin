import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.net.URI;

import org.silentsoft.actlist.plugin.ActlistPlugin;

public class Plugin extends ActlistPlugin {
	
	private Thread thread;
	
	public static void main(String args[]) throws Exception {
		debug();
	}
	
	public Plugin() {
		super("Stay Awake");
		
		setPluginVersion("1.0.2");
		setPluginAuthor("silentsoft.org", URI.create("https://github.com/silentsoft/actlist-stay-awake-plugin"));
		setPluginUpdateCheckURI(URI.create("http://actlist.silentsoft.org/api/plugin/0ebbebce/update/check"));
		setPluginDescription("When Stay Awake is activated, your machine will not gonna be a sleep mode. Stay Awake will always be with you !");
		
		setMinimumCompatibleVersion(1, 2, 6);
	}
	
	@Override
	protected void initialize() throws Exception {
		
	}
	
	@Override
	public void pluginActivated() throws Exception {
		thread = null;
		thread = new Thread(() -> {
			while (true) {
				try {
					Robot robot = new Robot();
					
					PointerInfo mousePointer = MouseInfo.getPointerInfo();
					if (mousePointer != null) {
						Point mouseLocation = mousePointer.getLocation();
						robot.mouseMove(mouseLocation.x, mouseLocation.y);
					}
					
					Thread.sleep(1000 * 20);
				} catch (InterruptedException e) {
					break;
				} catch (Exception e) {
					throwException(e);
					break;
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}
	
	@Override
	public void pluginDeactivated() throws Exception {
		thread.interrupt();
	}

}

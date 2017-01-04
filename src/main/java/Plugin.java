import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.silentsoft.actlist.plugin.ActlistPlugin;

public class Plugin extends ActlistPlugin {
	
	private Thread thread;
	
	public static void main(String args[]) throws Exception {}
	
	public Plugin() {
		super("Stay Awake");
		
		setPluginDescription("When Stay Awake is activated, will not gonna be sleep mode.");
		setPluginVersion("1.0.0");
		setPluginAuthor("Silentsoft (http://silentsoft.org)");
	}
	
	@Override
	protected void initialize() throws Exception {
		
	}
	
	@Override
	public void pluginActivated() throws Exception {
		thread = null;
		thread = new Thread(() -> {
			try {
				Robot robot = new Robot();
				
				while (true) {
					Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
					robot.mouseMove(mouseLocation.x, mouseLocation.y);
					
					Thread.sleep(1000 * 30);
				}
			} catch (Exception e) {
				
			}
		});
		thread.start();
	}
	
	@Override
	public void pluginDeactivated() throws Exception {
		thread.interrupt();
	}

}

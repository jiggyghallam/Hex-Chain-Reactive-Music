package reactogon;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * Loads all images
 *
 */
public class ImageLoader {
	Map<String, BufferedImage> images;

	/**
	 * Constructor loads all images
	 */
	public ImageLoader() {
		images = new HashMap<String, BufferedImage>();

		try {
			images.put("blankOff",
					ImageIO.read(new File("res/images/blankOff.png")));
			images.put("blankOn",
					ImageIO.read(new File("res/images/blankOn.png")));

			// Adds off tiles when play is true
			for (int i = 0; i < 6; i++) {
				images.put(
						"changeDirT" + i + "Off",
						ImageIO.read(new File("res/images/arrow/changeDir" + i
								+ "OffT.png")));
				// Adds off tiles when play is off
				images.put(
						"changeDirF" + i + "Off",
						ImageIO.read(new File("res/images/arrow/changeDir" + i
								+ "OffF.png")));

				// Adds on tiles when play is true
				images.put(
						"changeDirT" + i + "On",
						ImageIO.read(new File("res/images/arrow/changeDir" + i
								+ "OnT.png")));
				// Adds on tiles when play is false
				images.put(
						"changeDirF" + i + "On",
						ImageIO.read(new File("res/images/arrow/changeDir" + i
								+ "OnF.png")));

				images.put(
						"playT" + i + "Off",
						ImageIO.read(new File("res/images/play/play" + i
								+ "OffT.png")));

				images.put(
						"playT" + i + "On",
						ImageIO.read(new File("res/images/play/play" + i
								+ "OnT.png")));

				images.put(
						"playF" + i + "Off",
						ImageIO.read(new File("res/images/play/play" + i
								+ "OffF.png")));

				images.put(
						"playF" + i + "On",
						ImageIO.read(new File("res/images/play/play" + i
								+ "OnF.png")));

				images.put(
						"tab" + i,
						ImageIO.read(new File("res/images/tabs/tab" + (i + 1)
								+ ".png")));
			}

			images.put("explosionTOff", ImageIO.read(new File(
					"res/images/explosion/explosionOffT.png")));
			images.put("explosionTOn", ImageIO.read(new File(
					"res/images/explosion/explosionOnT.png")));
			images.put("explosionFOff", ImageIO.read(new File(
					"res/images/explosion/explosionOffF.png")));
			images.put("explosionFOn", ImageIO.read(new File(
					"res/images/explosion/explosionOnF.png")));
			images.put("stopTOff",
					ImageIO.read(new File("res/images/stop/stopOffT.png")));
			images.put("stopTOn",
					ImageIO.read(new File("res/images/stop/stopOnT.png")));
			images.put("stopFOff",
					ImageIO.read(new File("res/images/stop/stopOffF.png")));
			images.put("stopFOn",
					ImageIO.read(new File("res/images/stop/stopOnF.png")));

			images.put("changeDirT",
					ImageIO.read(new File("res/images/arrow/changeDirT.png")));
			images.put("changeDirF",
					ImageIO.read(new File("res/images/arrow/changeDirF.png")));
			images.put("stopT",
					ImageIO.read(new File("res/images/stop/stopT.png")));
			images.put("stopF",
					ImageIO.read(new File("res/images/stop/stopF.png")));
			images.put("explosionT", ImageIO.read(new File(
					"res/images/explosion/explosionT.png")));
			images.put("explosionF", ImageIO.read(new File(
					"res/images/explosion/explosionF.png")));
			images.put("playT",
					ImageIO.read(new File("res/images/play/playT.png")));
			images.put("playF",
					ImageIO.read(new File("res/images/play/playF.png")));
			
			images.put("warp",
					ImageIO.read(new File("res/images/warp/warp.png")));
			images.put("warpOff",
					ImageIO.read(new File("res/images/warp/warpOff.png")));
			images.put("warpOn",
					ImageIO.read(new File("res/images/warp/warpOn.png")));

			images.put("startOff", ImageIO.read(new File("res/images/startOff.png")));
			images.put("removeOff",
					ImageIO.read(new File("res/images/removeOff.png")));
			images.put("endOff", ImageIO.read(new File("res/images/endOff.png")));
			images.put("clear", ImageIO.read(new File("res/images/clear.png")));
			
			images.put("startOn", ImageIO.read(new File("res/images/startOn.png")));
			images.put("removeOn",
					ImageIO.read(new File("res/images/removeOn.png")));
			images.put("endOn", ImageIO.read(new File("res/images/endOn.png")));


			images.put("rotateSwitchT",
					ImageIO.read(new File("res/images/rotateT.png")));
			images.put("rotateSwitchF",
					ImageIO.read(new File("res/images/rotateF.png")));
			
			images.put("undo",
					ImageIO.read(new File("res/images/undo.png")));
			images.put("redo",
					ImageIO.read(new File("res/images/redo.png")));
			
			images.put("speed1",
					ImageIO.read(new File("res/images/tempo/speed1.png")));
			images.put("speed2",
					ImageIO.read(new File("res/images/tempo/speed2.png")));
			images.put("speed4",
					ImageIO.read(new File("res/images/tempo/speed4.png")));
			images.put("speed8",
					ImageIO.read(new File("res/images/tempo/speed8.png")));
			images.put("speed16",
					ImageIO.read(new File("res/images/tempo/speed16.png")));
			
			images.put("background",
					ImageIO.read(new File("res/images/background.png")));
			
			images.put("forground",
					ImageIO.read(new File("res/images/forground.png")));

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to load images");
			System.exit(-1);
		}
	}

	/**
	 * Takes a tile and its state and returs its repective image
	 * 
	 * @param tile {@link Tile}
	 * @param state {@link boolean}
	 * @return {@link BufferedImage}
	 */
	public BufferedImage getImage(Tile tile, boolean state) {
		StringBuilder type = new StringBuilder("");
		if (tile != null) {
			type.append(tile.getType());
			if (tile.getType().contains("changeDir")
					|| tile.getType().contains("play"))
				type.append(tile.getDirection());
			type.append(state ? "On" : "Off");
			return images.get(type.toString());

		}
		type.append("blank");
		type.append(state ? "On" : "Off");
		return images.get(type.toString());
	}

	/**
	 * Gets an image according to its name and state
	 * 
	 * @param tileName {@link String}
	 * @param state {@link boolean}
	 * @return Image {@link BufferedImage}
	 */
	public BufferedImage getImage(String tileName, boolean state) {
		return state ? images.get(tileName + "On") : images.get(tileName
				+ "Off");
	}

	/**
	 * Gets an image according to its name (usefull for images that dont have an
	 * on/off state
	 * 
	 * @param tileName {@link String}
	 * @return Image {@link BufferedImage}
	 */
	public BufferedImage getImage(String name) {
		return images.get(name);
	}

}

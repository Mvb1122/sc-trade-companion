package tools.sctrade.companion.exceptions;

import java.awt.Rectangle;
import java.util.Locale;

public class RectangleOutOfBoundsException extends RuntimeException {
  private static final long serialVersionUID = 8627902304541564806L;

  public RectangleOutOfBoundsException(Rectangle rectangle, Rectangle imageRectangle) {
    super(String.format(Locale.ROOT, "Rectangle '%s' is not within '%s'", rectangle.toString(),
        imageRectangle.toString()));
  }

}

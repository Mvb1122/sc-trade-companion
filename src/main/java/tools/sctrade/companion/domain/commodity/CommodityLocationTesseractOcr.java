package tools.sctrade.companion.domain.commodity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.sctrade.companion.domain.image.ImageManipulation;
import tools.sctrade.companion.domain.ocr.LocatedWord;
import tools.sctrade.companion.domain.ocr.OcrResult;
import tools.sctrade.companion.domain.ocr.TesseractOcr;
import tools.sctrade.companion.utils.ImageUtil;

public class CommodityLocationTesseractOcr extends TesseractOcr {
  private final Logger logger = LoggerFactory.getLogger(CommodityLocationTesseractOcr.class);

  public CommodityLocationTesseractOcr(List<ImageManipulation> preprocessingManipulations) {
    super(preprocessingManipulations);
  }

  @Override
  public OcrResult process(BufferedImage image) {
    image = keepLeftHalf(image);
    var words = tesseract.getWords(image, 0);
    words = removeNonWords(words);
    words = removeSingleCharacterWords(words);
    logger.trace(
        words.stream().map(n -> n.getText()).collect(Collectors.joining(System.lineSeparator())));

    return new OcrResult(words.stream()
        .map(n -> new LocatedWord(n.getText().toLowerCase(), n.getBoundingBox())).toList());
  }

  private BufferedImage keepLeftHalf(BufferedImage image) {
    return ImageUtil.crop(image, new Rectangle(0, 0, (image.getWidth() / 2), image.getHeight()));
  }
}

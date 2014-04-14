import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;

public class J7FilesDirs {

  public static void pathClass() {
    System.out.println("/ Path Class");
    System.out.println("------------\n");

    Path fakePath = Paths.get("/temp/textfile.txt");

    System.out.println(fakePath.toString());
    System.out.println(fakePath.getFileName());
    System.out.println(fakePath.getNameCount()); // Names at index 0, 1
    System.out.println(fakePath.getName(0));
    System.out.println(fakePath.getName(fakePath.getNameCount() - 1) + "\n");

    Path realPath = Paths.get("textfile.txt");

    try {
      Path path = realPath.toRealPath(LinkOption.NOFOLLOW_LINKS);
      System.out.println(path + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void managingFilesDirs() {
    System.out.println("/ Managing Files And Directories");
    System.out.println("--------------------------------\n");

    Path source = Paths.get("textfile.txt");
    Path target = Paths.get("newfile.txt");
    Path newDir = Paths.get("newdir");

    try {

      // Copying  to target
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
      // Deleting it
      Files.delete(target);
      // Creating new directory
      if (!Files.exists(newDir))
        Files.createDirectory(newDir);
      // Copying and moving to new dir
      Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
      Files.move(target, newDir.resolve(target.getFileName()),
          StandardCopyOption.REPLACE_EXISTING);

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void readingWritingTextFiles() {
    System.out.println("/ Reading And Writing Text Files");
    System.out.println("--------------------------------\n");

    Path source = Paths.get("textfile2.txt"); // Reading from
    Path target = Paths.get("textfile3.txt"); // Writing to
    Charset charset = Charset.forName("US-ASCII");
    ArrayList<String> lines = new ArrayList<>();

    // Reading from source
    try (BufferedReader reader = Files.newBufferedReader(source, charset)) {
      String line = null;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    // Writing to target
    try (BufferedWriter writer = Files.newBufferedWriter(target, charset)) {
      Iterator<String> iterator = lines.iterator();
      while (iterator.hasNext()) {
        String s = iterator.next();
        writer.append(s);
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
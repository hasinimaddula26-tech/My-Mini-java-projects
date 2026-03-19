import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

class DownloadTask implements Callable<String> {

    private String source;
    private String destination;

    public DownloadTask(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    @Override
    public String call() {

        try {
            InputStream in;

            // 🔹 If URL
            if (source.startsWith("http")) {

                System.out.println("Downloading from URL: " + source);

                URL url = new URL(source);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // 🔥 FIX (important)
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);

                in = connection.getInputStream();

            } else {
                // 🔹 Local file
                System.out.println("Copying local file: " + source);
                in = new FileInputStream(source);
            }

            String fileName = source.substring(source.lastIndexOf("/") + 1);
            FileOutputStream out = new FileOutputStream(destination + "/" + fileName);

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();
            out.close();

            return "✅ Done: " + fileName;

        } catch (Exception e) {
            return "❌ Failed: " + source + " | " + e.getMessage();
        }
    }
}

public class DownloadManager {

    private static final int THREADS = 3;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of files: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[] sources = new String[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter URL or file path " + (i + 1) + ": ");
            sources[i] = sc.nextLine();
        }

        System.out.print("Enter destination folder: ");
        String destination = sc.nextLine();

        new File(destination).mkdirs();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        List<Callable<String>> tasks = new ArrayList<>();

        for (String src : sources) {
            tasks.add(new DownloadTask(src, destination));
        }

        try {
            List<Future<String>> results = executor.invokeAll(tasks);

            System.out.println("\nResults:");
            for (Future<String> result : results) {
                System.out.println(result.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
        sc.close();
    }
}
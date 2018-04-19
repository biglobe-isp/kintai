package jp.co.biglobe.lib.publication.batch;

import jp.co.biglobe.lib.pipes_and_filters.publication.csv.CsvFileWriterBuilder;
import jp.co.biglobe.lib.pipes_and_filters.publication.csv.DefaultCsvFileParameters;
import jp.co.biglobe.lib.pipes_and_filters.publication.file.FileWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.QuoteMode;

import java.io.File;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public class AugmentedCsvFileWriterBuilder {

    private CsvFileWriterBuilder builder;

    private CSVFormat csvFormat = DefaultCsvFileParameters.DEFAULT_CSV_FORMAT;

    private Optional<List<String>> header = Optional.empty();

    private OpenOption[] openOptions;

    public AugmentedCsvFileWriterBuilder(Path path, OpenOption... openOptions) {
        builder = new CsvFileWriterBuilder(path);
        this.openOptions = openOptions;
    }

    public AugmentedCsvFileWriterBuilder(File file) {
        builder = new CsvFileWriterBuilder(file);
    }

    public AugmentedCsvFileWriterBuilder setDoubleQuoteSeparator() {
        csvFormat = CSVFormat.RFC4180
                .withQuoteMode(QuoteMode.ALL)
                .withRecordSeparator(System.lineSeparator());

        return this;
    }

    public FileWriter<List<String>> build() throws IOException {
        CSVFormat format = header
                .map(h -> csvFormat.withHeader(h.toArray(new String[0])))
                .orElse(csvFormat);

        if (openOptions != null && openOptions.length > 0) {
            builder.withOpenOptions(openOptions);
        }

        return builder.withCsvFormat(format).build();
    }

}

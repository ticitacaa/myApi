package com.example.myapi2.repository.output.ap;

import com.example.myapi2.Constants;
import com.example.myapi2.domain.output.period.PeriodOutput;
import com.example.myapi2.domain.output.period.PeriodIndexes;
import com.example.myapi2.domain.output.period.PeriodPriceOutput;
import com.example.myapi2.exception.exhandler.QueryFailedException;
import com.example.myapi2.repository.output.PeriodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

import static com.example.myapi2.Constants.IP_ADDR_PORT;
import static com.example.myapi2.Constants.PATH;

@Slf4j
@Repository
public class PeriodRepositoryAp implements PeriodRepository {

    private static final String TR = "m1082";
    private static final int PRICE_START = 489;
    private static final int PRICE_TOTAL_NUM = 59;
    private static final int HNAME_START = 16;
    private static final int HNAME_END = 128;


    @Override
    public PeriodOutput findData(String exid, String symbol, Object periodInput) {

        PeriodOutput periodOutput = new PeriodOutput();

        String inPacket = PATH + " " + IP_ADDR_PORT + " " + TR + " '" + (String) periodInput + "'";

        try {
            // 리눅스 명령어를 실행할 때는 "/bin/bash"와 "-c"를 사용하여 실행합니다.
            Process process = Runtime.
                    getRuntime().
                    exec(new String[] {
                            "/bin/bash",
                            "-c",
                            inPacket }
                    );

            // 명령어 실행 결과를 읽어옵니다.
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String outPacket = null;
            while ((line = reader.readLine()) != null) {
                outPacket = line;
            }

            Optional<String> optionalOutPacket = Optional.ofNullable(outPacket);

            if(!optionalOutPacket.get().equals("<XtCallTran.cc:48> XtCallTran[0]")) {

                String value = optionalOutPacket.get();

                periodOutput.setExid(exid);
                periodOutput.setSymbol(symbol);
                setHname(periodOutput, value);
                setPrice(periodOutput, value);
            } else {
                throw new QueryFailedException();
            }

            // 프로세스가 종료될 때까지 기다립니다.
            int exitCode = process.waitFor();
            System.out.println("Exited with error code " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        return periodOutput;
    }

    public String getPriceData(String output) {
        return subStrByte(output, PRICE_START, output.getBytes().length);
    }

    public void setHname(PeriodOutput dailyOutput, String output) {
        dailyOutput.setHname(subStrByte(output, HNAME_START, HNAME_END).trim());
    }
    public void setPrice(PeriodOutput dailyOutput, String output) {

        String priceData = getPriceData(output);
        int count = priceData.getBytes().length / PRICE_TOTAL_NUM;
        int startLength = 0;
        int endLength = 0;
        for(int i = 0; i < count; i++) {

            PeriodPriceOutput periodPriceOutput = new PeriodPriceOutput();

            startLength = i * PRICE_TOTAL_NUM;
            endLength = startLength + PeriodIndexes.LOCDT.getLength();
            periodPriceOutput.setLocdt(priceData.substring(startLength, endLength).trim());

            startLength += PeriodIndexes.LOCDT.getLength();
            endLength += PeriodIndexes.OPENPRC.getLength();
            periodPriceOutput.setOpenprc(priceData.substring(startLength, endLength).trim());

            startLength += PeriodIndexes.OPENPRC.getLength();
            endLength += PeriodIndexes.HIGHPRC.getLength();
            periodPriceOutput.setHighprc(priceData.substring(startLength, endLength).trim());

            startLength += PeriodIndexes.HIGHPRC.getLength();
            endLength += PeriodIndexes.LOWPRC.getLength();
            periodPriceOutput.setLowprc(priceData.substring(startLength, endLength).trim());


            startLength += PeriodIndexes.LOWPRC.getLength();
            endLength += PeriodIndexes.CLOSEPRC.getLength();
            periodPriceOutput.setCloseprc(priceData.substring(startLength, endLength).trim());

            startLength += PeriodIndexes.CLOSEPRC.getLength();
            endLength += PeriodIndexes.VOLUME.getLength();
            periodPriceOutput.setVolume(priceData.substring(startLength, endLength).trim());

            dailyOutput.getData().add(periodPriceOutput);
        }

    }

    public String subStrByte(String str, int slen, int elen ) {

        if(!str.isEmpty()) {

            StringBuffer sbStr = new StringBuffer(elen - slen);

            int nCnt = 0;
            for(char ch: str.toCharArray()) {
                nCnt += String.valueOf(ch).getBytes().length;
                if(nCnt > slen && nCnt <= elen) sbStr.append(ch);
            }

            return sbStr.toString();
        }
        else {
            return "";
        }
    }
}

package org.mow.it.now.batch.step;

import org.mow.it.now.core.FinalMowPosition;
import org.mow.it.now.core.ProgrammedMow;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class SimulationProcessor implements ItemProcessor<ProgrammedMow, FinalMowPosition> {
    @Override
    public FinalMowPosition process(ProgrammedMow item) {
        item.launch();
        return new FinalMowPosition(item.mow().position());
    }
}

package uyora.core.quest.dev;


import uyora.core.quest.Quest;
import uyora.core.quest.QuestProgressionTracker;
import uyora.core.quest.stage.QuestStageKill;

public class killPigs extends QuestStageKill {

    public killPigs(String id, Quest quest) {
        super(id, quest);
    }

    @Override
    public void checkCompletion(QuestProgressionTracker<?> tracker) {
        testTracker t = (testTracker) tracker;
        System.out.println("Check fired");
        if(t.pigCounter >= 5) {
            System.out.println("Quest succeed");
            // quest.rewardPlayer(t.getUyoraCharacter());
            // t.getUyoraCharacter().removeTracker(quest.getId());
        }
    }

    @Override
    public QuestProgressionTracker<?> setupTracker() {
        return new testTracker(this);
    }

}

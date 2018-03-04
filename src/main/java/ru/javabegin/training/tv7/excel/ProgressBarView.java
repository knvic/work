package ru.javabegin.training.tv7.excel;

import org.springframework.stereotype.Component;
import sun.misc.Contended;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
@Component
@ViewScoped
public class ProgressBarView  implements Serializable{

        private Integer progress;

        public Integer getProgress() {
            if(progress == null) {
                progress = 0;
            }
            else {
                progress = progress + (int)(Math.random() * 35);

                if(progress > 100)
                    progress = 100;
            }

            return progress;
        }

        public void setProgress(Integer progress) {
            this.progress = progress;
        }

        public void onComplete() {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
        }

        public void cancel() {
            progress = null;
        }
}

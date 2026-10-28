package org.example.Domain;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


    @XmlRootElement(name = "zboruri")
    public class ZborListWrapper {

        private List<Zbor> zboruri;

        @XmlElement(name = "zbor")
        public List<Zbor> getZboruri() {
            return zboruri;
        }

        public void setZboruri(List<Zbor> zboruri) {
            this.zboruri = zboruri;
        }


        public ZborListWrapper() {
            this.zboruri = new ArrayList<>();
        }
}

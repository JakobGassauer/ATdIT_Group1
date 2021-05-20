package library.persistence;

import library.model.implementation.*;
import library.persistence.implementation.*;

import java.util.List;

public interface Factory {
    Service createService();

}

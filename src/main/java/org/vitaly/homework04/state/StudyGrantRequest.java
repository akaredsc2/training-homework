package org.vitaly.homework04.state;

import static org.vitaly.util.InputChecker.requireNonNull;

/**
 * Created by vitaly on 2017-03-08.
 */
public class StudyGrantRequest {
    private String candidate;
    private String description;
    private GrantRequestState state;

    private StudyGrantRequest(String candidate, String description) {
        this.candidate = candidate;
        this.description = description;
        this.state = new CreatedState();
    }

    public GrantRequestState getState() {
        return state;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getDescription() {
        return description;
    }

    public void revokeRequest() {
        state.revoke();
    }

    public void pendRequest() {
        state.pend();
    }

    public void considerRequest() {
        state.consider();
    }

    public void confirmRequest() {
        state.confirm();
    }

    public void rejectRequest() {
        state.reject();
    }

    public static StudyGrantRequest newStudyGrantRequest(String candidate, String description) {
        requireNonNull(candidate, "Candidate must not be null!");
        requireNonNull(description, "Description must not be null!");

        return new StudyGrantRequest(candidate, description);
    }

    public abstract class GrantRequestState {
        public void revoke() {
        }

        public void pend() {
        }

        public void consider() {
        }

        public void confirm() {
        }

        public void reject() {
        }
    }

    public class CreatedState extends GrantRequestState {
        @Override
        public void revoke() {
            StudyGrantRequest.this.state = new RevokedState();
        }

        @Override
        public void pend() {
            StudyGrantRequest.this.state = new PendingState();
        }
    }

    public class RevokedState extends GrantRequestState {
    }

    public class PendingState extends GrantRequestState {
        @Override
        public void revoke() {
            StudyGrantRequest.this.state = new RevokedState();
        }

        @Override
        public void consider() {
            StudyGrantRequest.this.state = new ConsideringState();
        }
    }

    public class ConsideringState extends GrantRequestState {
        @Override
        public void revoke() {
            StudyGrantRequest.this.state = new RevokedState();
        }

        @Override
        public void pend() {
            StudyGrantRequest.this.state = new PendingState();
        }

        @Override
        public void confirm() {
            StudyGrantRequest.this.state = new ConfirmedState();
        }

        @Override
        public void reject() {
            StudyGrantRequest.this.state = new RejectedState();
        }
    }

    public class ConfirmedState extends GrantRequestState {
    }

    public class RejectedState extends GrantRequestState {
    }
}

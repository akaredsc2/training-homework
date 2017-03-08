package org.vitaly.homework04.state;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vitaly on 2017-03-08.
 */
public class StudyGrantRequestTest {
    private StudyGrantRequest request;
    private String candidate;
    private String description;

    @Before
    public void setUp() throws Exception {
        candidate = "candidate";
        description = "description";
        request = StudyGrantRequest.newStudyGrantRequest(candidate, description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newStudyGrantRequestWithNullCandidateShouldThrowAnException() throws Exception {
        StudyGrantRequest.newStudyGrantRequest(null, description);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newStudyGrantRequestWithNullDescriptionShouldThrowAnException() throws Exception {
        StudyGrantRequest.newStudyGrantRequest(candidate, null);
    }

    @Test
    public void newRequestHasCreatedStatus() throws Exception {
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.CreatedState.class));
    }

    @Test
    public void newRequestSuccessfullyRevokes() throws Exception {
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RevokedState.class));
    }

    @Test
    public void newRequestSuccessfullyPends() throws Exception {
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.PendingState.class));
    }

    @Test
    public void newRequestFailsToBecomeConsidered() throws Exception {
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConsideringState.class)),
                instanceOf(StudyGrantRequest.CreatedState.class)));
    }

    @Test
    public void newRequestFailsToBecomeConfirmed() throws Exception {
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConfirmedState.class)),
                instanceOf(StudyGrantRequest.CreatedState.class)));
    }

    @Test
    public void newRequestFailsToBecomeRejected() throws Exception {
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RejectedState.class)),
                instanceOf(StudyGrantRequest.CreatedState.class)));
    }

    @Test
    public void revokedRequestDoesNotChangeStateOnRevoke() throws Exception {
        request.revokeRequest();
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RevokedState.class));
    }

    @Test
    public void revokedRequestFailsToBecomePending() throws Exception {
        request.revokeRequest();
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.PendingState.class)),
                instanceOf(StudyGrantRequest.RevokedState.class)));
    }

    @Test
    public void revokedRequestFailsToBecomeConsidered() throws Exception {
        request.revokeRequest();
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConsideringState.class)),
                instanceOf(StudyGrantRequest.RevokedState.class)));
    }

    @Test
    public void revokedRequestFailsToBecomeConfirmed() throws Exception {
        request.revokeRequest();
        request.confirmRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConfirmedState.class)),
                instanceOf(StudyGrantRequest.RevokedState.class)));
    }

    @Test
    public void revokedRequestFailsToBecomeRejected() throws Exception {
        request.revokeRequest();
        request.rejectRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RejectedState.class)),
                instanceOf(StudyGrantRequest.RevokedState.class)));
    }

    @Test
    public void pendingRequestSuccessfullyRevokes() throws Exception {
        request.pendRequest();
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RevokedState.class));
    }

    @Test
    public void pendingRequestDoesNotChangeStateOnPend() throws Exception {
        request.pendRequest();
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.PendingState.class));
    }

    @Test
    public void pendingRequestSuccessfullyConsider() throws Exception {
        request.pendRequest();
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.ConsideringState.class));
    }

    @Test
    public void pendingRequestFailsToBecomeConfirmed() throws Exception {
        request.pendRequest();
        request.confirmRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConfirmedState.class)),
                instanceOf(StudyGrantRequest.PendingState.class)));
    }

    @Test
    public void pendingRequestFailsToBecomeRejected() throws Exception {
        request.pendRequest();
        request.rejectRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RejectedState.class)),
                instanceOf(StudyGrantRequest.PendingState.class)));
    }

    @Test
    public void consideringRequestSuccessfullyRevokes() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RevokedState.class));
    }

    @Test
    public void consideringRequestSuccessfullyPends() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.PendingState.class));
    }

    @Test
    public void consideringRequestDoesNotChangeStateOnConsider() throws Exception {
        request.pendRequest();
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.ConsideringState.class));
    }

    @Test
    public void consideringRequestSuccessfullyConfirmed() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.ConfirmedState.class));
    }

    @Test
    public void consideringRequestSuccessfullyRejected() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RejectedState.class));
    }

    @Test
    public void confirmedRequestFailsToBecomeRevoked() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RevokedState.class)),
                instanceOf(StudyGrantRequest.ConfirmedState.class)));
    }

    @Test
    public void confirmedRequestFailsToBecomePending() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.PendingState.class)),
                instanceOf(StudyGrantRequest.ConfirmedState.class)));
    }

    @Test
    public void confirmedRequestFailsToBecomeConsidered() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConsideringState.class)),
                instanceOf(StudyGrantRequest.ConfirmedState.class)));
    }

    @Test
    public void confirmedRequestDoesNotChangeStateOnConfirm() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        request.confirmRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.ConfirmedState.class));
    }

    @Test
    public void confirmedRequestFailsToBecomeRejected() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.confirmRequest();
        request.rejectRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RejectedState.class)),
                instanceOf(StudyGrantRequest.ConfirmedState.class)));
    }

    @Test
    public void rejectedRequestFailsToBecomeRevoked() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        request.revokeRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.RevokedState.class)),
                instanceOf(StudyGrantRequest.RejectedState.class)));
    }

    @Test
    public void rejectedRequestFailsToBecomePending() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        request.pendRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.PendingState.class)),
                instanceOf(StudyGrantRequest.RejectedState.class)));
    }

    @Test
    public void rejectedRequestFailsToBecomeConsidered() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        request.considerRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConsideringState.class)),
                instanceOf(StudyGrantRequest.RejectedState.class)));
    }

    @Test
    public void rejectedRequestFailsToBecomeConfirmed() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        request.confirmRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, allOf(
                not(instanceOf(StudyGrantRequest.ConfirmedState.class)),
                instanceOf(StudyGrantRequest.RejectedState.class)));
    }

    @Test
    public void rejectedRequestDoesNotChangeStateOnReject() throws Exception {
        request.pendRequest();
        request.considerRequest();
        request.rejectRequest();
        request.rejectRequest();
        StudyGrantRequest.GrantRequestState actualRequestState = request.getState();

        assertThat(actualRequestState, instanceOf(StudyGrantRequest.RejectedState.class));
    }
}
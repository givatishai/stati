/**
 * Created by Sigal on 5/21/2016.
 */
$(document).ready(function () {

    $(".newSupervisor").change(function () {
        var teacherUid = $(this).val();
        var studentUid = $(this).attr("uid");
        $.post("/change_supervisor.json?", "supervisor=" + teacherUid + "&&studentUid=" + studentUid, newSupervisorResponse);
    });
    
    function newSupervisorResponse(data, status) {
        alert(data.error ? "Failure" : "Success");

    }

    $("#readMessage").click(function () {
        if(($this).is(':checked')) {
            var messageId = $(this).attr("oid");
        }
        $.post("/mark_as_read.json?", "messageId=" + messageId, markAsReadResponse);
        
    });


    $("#submitMessageButton").click(function () {
        var title = $("#messageTitle").val();
        var message = $("#messageDetails").val();
        $(location).attr('href',"/message_sent?title=" + title + "&&message=" + message);
        
      //  window.location.replace("/message_sent?title=" + title + "&&message=" + message);

    });
    
    $("#showRegularUsersTable").click(function () {
        if ($("#regularUsersTable").is(':visible')) {
            $("#regularUsersTable").fadeOut("slow");
            $(this).text("Show");
        } else {
            $("#regularUsersTable").fadeIn("slow");
            $(this).text("Hide");
        }
    });

    $("#showPendingVideosTable").click(function () {
        if ($("#pendingVideosTable").is(':visible')) {
            $("#pendingVideosTable").fadeOut("slow");
            $(this).text("Show");
        } else {
            $("#pendingVideosTable").fadeIn("slow");
            $(this).text("Hide");
        }
    });

    $("#showPendingUsersTable").click(function () {
        if ($("#pendingUsersTable").is(':visible')) {
            $("#pendingUsersTable").fadeOut("slow");
            $(this).text("Show");
        } else {
            $("#pendingUsersTable").fadeIn("slow");
            $(this).text("Hide");
        }
    });

    $("#showPendingMessagesTable").click(function () {
        if ($("#pendingMessagesTable").is(':visible')) {
            $("#pendingMessagesTable").fadeOut("slow");
            $(this).text("Show");
        } else {
            $("#pendingMessagesTable").fadeIn("slow");
            $(this).text("Hide");
        }
    });



    $(".setActive").click(function () {
        var pendingUserUid = $(this).attr("uid");
        $.post("/set_active.json?", "pendingUserUid=" + pendingUserUid, setActiveResponse);

    });
    function setActiveResponse(data, status) {
        var uid = data.uid;
        var elementId = "#pendingUser" + uid;

        if (data.error) {
            alert("Failure");
        } else {
            $(elementId).fadeOut("slow");

        }

    }


    $(".understand-explanation-true").click(function () {
        var questionIndex = $(this).attr("question-index");
        $("#understand_explanation_" + questionIndex).hide();
    });

    $("button[id^='submit_answer_']").click(function () {
        $(this).attr("disabled", "disabled");
        $("#user_answer_" + $(this).attr("question-index")).attr("disabled", "disabled");
        $("#activeQuestion").val($(this).attr("question-index"));
        var userAnswerId = "#user_answer_" + $(this).attr("question-index");
        var data = "userAnswer=" + $(userAnswerId).val();
        var answerId = "#answer_" + $(this).attr("question-index");
        data = data + "&" + "answer=" + $(answerId).val();
        var subjectOid = "#question_subject_oid";
        data = data + "&" + "subjectOid=" + $(subjectOid).val();
        data = data + "&" + "questionIndex=" + $(this).attr("question-index");
        createWizrad($("#activeQuestion").val());
        initWizard();

        $.post("/chech-answer.json?", data, checkAnswerResponse);
    });

    $(".understand-explanation-false").click(function () {
        var questionIndex = $(this).attr("question-index");
        var description = "desc";
        var question = "ques";
        var data = "description=" + description + "&question=" + question;
        $.post("/request-video.json?", data, requestVideoResponse);
    });

    function requestVideoResponse(data, status) {

    }

    function checkAnswerResponse(data, status) {
        var questionIndex = data.questionIndex;
        if (data.rightAnswer) {
            $("#submit_answer_" + questionIndex).hide();
            $("#right_answer_alert_" + questionIndex).show();
            // setTimeout(function() { $("#right_answer_alert_" + questionIndex).hide(); }, 5000);
        } else {
            $("#submit_answer_" + questionIndex).hide();
            $("#wrong_answer_alert_" + questionIndex).show();
            // setTimeout(function() { $("#wrong_answer_alert_" + questionIndex).hide(); }, 5000);
        }
    }


    $("button[id^='explanation_']").click(function () {
        var questionIndex = $(this).attr("question-index");
        $("#wrong_answer_alert_" + questionIndex).hide();
        $("#steps-wizard-container").show(500);
        window.location.href = window.location + "#steps-wizard-container";
    });

    $(".next a").click(function () {
        $(".wizard-steps").hide();
        var questionIndex = $(this).attr("question-index");
        var stepIndex = $(this).attr("step-index");
        $("#wizardSteps" + questionIndex).show();
        $("div[class^='wizard-form-step']").hide();
        $(".wizard-form-step_" + questionIndex + "" + stepIndex).show();

    });


});

function validateUserSignupForm() {

    var valid = true;

    if (($("#signupName").val().length == 0)) {
        $("#signupNameAlert").show();
        valid = false;
    }
    else {
        $("#signupNameAlert").hide();

    }
    if (($("#signupUserName").val().length == 0)) {
        $("#signupUserNameAlert").show();
        valid = false;
    }
    else {
        $("#signupUserNameAlert").hide();
    }
    if (($("#signupPassword").val().length == 0)) {
        $("#signupPasswordAlert").show();
        valid = false;
    }
    else {
        $("#signupPasswordAlert").hide();

    }
    if (($("#signupRepeatPassword").val().length == 0)) {
        $("#signupRepeatPasswordAlert").show();
        valid = false;
    }
    else {
        $("#signupRepeatPasswordAlert").hide();
    }
    if (($("#signupPassword").val().length != 0) && ($("#signupRepeatPassword").val().length != 0) && ($("#signupPassword").val() != $("#signupRepeatPassword").val())) {
        $("#signupConfirmPasswordAlert").show();
        valid = false;
    }
    else {
        $("#signupConfirmPasswordAlert").hide();
    }

    return valid;
}


function validateTutorSignupForm() {

    var valid = true;
    if (($("#signupName").val().length == 0)) {
        $("#signupNameAlert").show();
        valid = false;
    } else {
        $("#signupNameAlert").hide();
    }

    if (($("#signupTelephoneNumber").val().length == 0)) {
        $("#signupTelephoneNumberAlert").show();
        valid = false;
    } else {
        $("#signupTelephoneNumberAlert").hide();
    }
    if (($("#signupEmail").val().length == 0)) {
        $("#signupEmailAlert").show();
        valid = false;
    } else {
        $("#signupEmailAlert").hide();
    }

// else
// {
//
// }
    return valid;
}

function createWizrad(question) {
    $("#steps-progress-bar").children().remove();
    $("#wizard-steps").children().remove();

    var inputIdsPrefix = "question-step-" + $("#activeQuestion").val() + "-";
    var numberOfSteps = $("input[id^=" + inputIdsPrefix + "]").size();
    var count = numberOfSteps + 1;
    var division = 2;
    if (numberOfSteps == 2) {
        division = 6;
    } else if (numberOfSteps == 3) {
        division = 4;
    } else if (numberOfSteps == 4) {
        division = 3;

    } else if (numberOfSteps == 6) {
        division = 2;
    }
    for (var i = 1; i < count; i++) {
        if (i == 1) {
            $('<a>', {
                class: 'btn btn-success btn-circle',
                href: '#step-' + i
            }).append(i).appendTo($('<div>', {
                class: 'stepwizard-step col-xs-' + division
            }).appendTo("#steps-progress-bar"));

        } else {
            $('<a>', {
                class: 'btn btn-default btn-circle',
                href: '#step-' + i,
                disabled: 'disabled'
            }).append(i).appendTo($('<div>', {
                class: 'stepwizard-step col-xs-' + division
            }).appendTo("#steps-progress-bar"));

        }

        var stepElement = $('<div>', {
            class: 'panel panel-primary setup-content',
            id: 'step-' + i,
            style: i == 1 ? "display: block" : "display: none"
        }).appendTo("#wizard-steps");

        $('<h3>', {
            class: 'panel-title'
        }).append("STEP #" + i).appendTo($('<div>', {
            class: 'panel-heading'
        }).appendTo(stepElement));

        var stepBody = $('<div>', {
            class: 'panel-body'
        }).appendTo(stepElement);

        $('<div>', {}).append($("#" + inputIdsPrefix + i).val()).appendTo(stepBody);

        var stepButtons = $('<ul>', {
            class: 'pager'
        }).appendTo($('<nav>', {}).appendTo(stepBody));

        var nextButtonText = i == (count - 1) ? "Finish" : "Next";

        if (i == (count - 1)) {
            $('<span aria-hidden="true">', {}).append("&rarr;").appendTo($('<a>', {
                class: 'nextBtn',
                href: '#steps-wizard-container'
            }).append(nextButtonText).appendTo($('<li>', {
                class: 'next'
            }).on("click", function () {
                $("#steps-wizard-container").hide(500);
                $("#understand_explanation_" + $("#activeQuestion").val()).show()
            }).appendTo(stepButtons)));

        } else {
            $('<span aria-hidden="true">', {}).append("&rarr;").appendTo($('<a>', {
                class: 'nextBtn',
                href: '#'
            }).append(nextButtonText).appendTo($('<li>', {
                class: 'next',
            }).appendTo(stepButtons)));
        }


        if (i != 1) {
            $('<span aria-hidden="true">', {}).append("&larr;").appendTo($('<a>', {
                class: 'prevBtn',
                href: '#'
            }).append("Previous").appendTo($('<li>', {
                class: 'previous'
            }).appendTo(stepButtons)));
        }
    }
}

function initWizard() {
    var navListItems = $('div.setup-panel div a'),
        allWells = $('.setup-content'),
        allNextBtn = $('.nextBtn'),
        allPrevBtn = $('.prevBtn');

    allWells.hide();

    navListItems.click(function (e) {
        e.preventDefault();
        var $target =
                $($(this).attr('href')),
            $item = $(this);
        var nextStepWizard = $(this).text();
        var inputIdsPrefix = "question-step-" + $("#activeQuestion").val() + "-";

        var numberOfSteps = $("input[id^=" + inputIdsPrefix + "]").size();
        switch (numberOfSteps) {
            case 2:
                if (nextStepWizard == 1)
                    $('.stepwizard .progress-bar').animate({width: '0%'}, 0);
                if (nextStepWizard == 2)
                    $('.stepwizard .progress-bar').animate({width: '100%'}, 0);
                break;
            case 3:
                if (nextStepWizard == 1)
                    $('.stepwizard .progress-bar').animate({width: '0%'}, 0);
                if (nextStepWizard == 2)
                    $('.stepwizard .progress-bar').animate({width: '50%'}, 0);
                if (nextStepWizard == 3)
                    $('.stepwizard .progress-bar').animate({width: '100%'}, 0);
                break;
            case 4:
                if (nextStepWizard == 1)
                    $('.stepwizard .progress-bar').animate({width: '0%'}, 0);
                if (nextStepWizard == 2)
                    $('.stepwizard .progress-bar').animate({width: '33%'}, 0);
                if (nextStepWizard == 3)
                    $('.stepwizard .progress-bar').animate({width: '67%'}, 0);
                if (nextStepWizard == 4)
                    $('.stepwizard .progress-bar').animate({width: '100%'}, 0);
                break;
            case 6:
                if (nextStepWizard == 1)
                    $('.stepwizard .progress-bar').animate({width: '0%'}, 0);
                if (nextStepWizard == 2)
                    $('.stepwizard .progress-bar').animate({width: '20%'}, 0);
                if (nextStepWizard == 3)
                    $('.stepwizard .progress-bar').animate({width: '40%'}, 0);
                if (nextStepWizard == 4)
                    $('.stepwizard .progress-bar').animate({width: '60%'}, 0);
                if (nextStepWizard == 5)
                    $('.stepwizard .progress-bar').animate({width: '80%'}, 0);
                if (nextStepWizard == 6)
                    $('.stepwizard .progress-bar').animate({width: '100%'}, 0);
                break;

        }

        if (!$item.hasClass('disabled')) {
            navListItems.removeClass('btn-success').addClass('btn-default');
            //navListItems.addClass('btn-default');
            $item.addClass('btn-success');
            allWells.hide();
            $target.show();
            $target.find('input:eq(0)').focus();
        }
    });

    allNextBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
            curInputs = curStep.find("input[type='text'],input[type='url']"),
            isValid = true;

        $(".form-group").removeClass("has-error");
        for (var i = 0; i < curInputs.length; i++) {
            if (!curInputs[i].validity.valid) {
                isValid = false;
                $(curInputs[i]).closest(".form-group").addClass("has-error");
            }
        }

        if (isValid) {
            nextStepWizard.removeAttr('disabled').trigger('click');
        }
    });


    allPrevBtn.click(function () {
        var curStep = $(this).closest(".setup-content"),
            curStepBtn = curStep.attr("id"),
            prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a");
        prevStepWizard.removeAttr('disabled').trigger('click');
    });


    $('div.setup-panel div a.btn-success').trigger('click');

}

function loginAsUser(userName, password,admin) {
    window.location = "/login?loginUserName=" + userName + "&&loginPassword=" + password + "&&admin=" + admin ;
}

function analyzeStudent(uid, subjectOid) {
    window.location = "/analyze?userid=" + uid + "&&subjectOid=" + subjectOid;
}

function changeSupervisor(sel) {
    var teacherUid = sel.value;
    var studentUid = $(sel).attr("uid");
    window.location = "/change_supervisor?supervisor=" + teacherUid;
}



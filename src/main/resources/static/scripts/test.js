(function($, undefined) {
    var _instances = 0;
    var dialog = $.extend({}, $.ui.dialog.prototype),
        _super = {
            _create: dialog._create,
            _destroy: dialog._destroy,
            open: dialog.open,
            close: dialog.close
        };
    dialog = $.extend(dialog, {
        options: $.extend({}, dialog.options, {
            modal: true,
            autoOpen: false,
            resizable: false,
            fadeOverlay: true,
            fancyAnimation: true,
            beforeOpen: function(e, ui) {},
            beforeClose: function(e, ui) {},
            afterOpen: function(e, ui) {},
            afterClose: function(e, ui) {},
            hide: "fade"
        }),
        _fancyAnimation: null,
        _create: function() {
            _super._create.call(this);
        },
        _destroy: function() {
            var self = this;
            if (self.overlay && self.options.fadeOverlay) {
                self.overlay.$el.hide("fade", 200, function() {
                    _super._destroy.call(self);
                });
            } else {
                return _super._destroy.call(this);
            }
        },
        open: function(e) {
            var self = this;
            // let's trigger beforeOpen     
            self.options.beforeOpen.call(self, e);
            self = _super.open.call(self, e);

            // get height and width of dialog                
            var height = self.uiDialog.outerHeight(true),
                width = self.uiDialog.outerWidth(true);

            // prepare fancy animation         
            if (self.options.fancyAnimation) {
                self._fancyAnimation = {
                    animate: {
                        opacity: 1,
                        height: height,
                        width: width
                    },
                    options: {
                        duration: 150,
                        step: function() {
                            self.uiDialog.position({
                                my: "center",
                                at: "center",
                                of: window
                            });
                        },
                        complete: function() {
                            self.element.fadeTo(50, 1);
                            self.uiDialog.css("height", "auto");
                        }
                    }
                };

                // hide content               
                self.element.fadeTo(0, 0); // get dialog element and position it 
                // make dialog transparent and set half height/width             
                self.uiDialog.css("opacity", 0).height(height / 2).width(width / 2);
            }

            $(window).bind("resize." + self.widgetBaseClass + "-" + self.id, function() {
                self.uiDialog.position({
                    my: "center",
                    at: "center",
                    of: window
                });
            });

            // if modal and fadeOverlay                
            if (self.options.modal && self.options.fadeOverlay) {
                // get overlay   
                self.overlay.$el.hide().show("fade", 200, function() {
                    if (self.options.fancyAnimation) {
                        self.uiDialog.stop(true, true)
                            .animate(self._fancyAnimation.animate, self._fancyAnimation.options);
                    }
                });
            } else {
                if (self.options.fancyAnimation) {
                    self.uiDialog.stop(true, true)
                        .animate(self._fancyAnimation.animate, self._fancyAnimation.options);
                }
            }

            // let's trigger afterOpen           
            self.options.afterOpen.call(self, e);
            return self;
        },
        close: function(e) {
            var self = this;
            // let's trigger beforeClose     
            self.options.beforeClose.call(self, e);
            $(window).unbind("resize." + self.widgetBaseClass + "-" + self.id);
            if (self.overlay && self.options.fadeOverlay) {
                self.overlay.$el.hide("fade", 200, function() {
                    self = _super.close.call(self, e);
                    // let's trigger beforeClose 
                    self.options.afterClose.call(self, e);
                });
            } else {
                self = _super.close.call(self, e);
                // let's trigger beforeClose 
                self.options.afterClose.call(self, e);
            }
            return self;
        }
    });

    $.widget("xui.dialog", dialog);

    if (!$.xui) { $.xui = {}; }

    $.xui.locale = {
        "en-US": {
            yes: "Yes",
            no: "No",
            close: "Close",
            confirm: "Confirm",
            alert: "Alert"
        },
        "sv-SE": {
            yes: "Ja",
            no: "Nej",
            close: "Stäng",
            confirm: "Bekräfta",
            alert: "Uppmärksamma"
        }
    };

    $.xui.alert = function(content, title, options){
        var defaults = {
            locale: "en-US"
        };
        options = $.extend({}, defaults, options);
        var locale = $.xui.locale[options.locale];
        return $("<div></div>")
            .append(content)
            .dialog($.extend({
                title: title || locale.alert,
                autoOpen: true,
                close: function(){
                    $(this).dialog("destroy");
                },
                buttons: {
                    Close: function() {
                        $(this).dialog("close");
                    }
                }
            }, options));
    };

    $.xui.confirm = function(content, title, options){
        var defaults = {
            locale: "en-US"
        };
        options = $.extend({}, defaults, options);
        var locale = $.xui.locale[options.locale],
            dfd = $.Deferred(),
            dialog = $("<div></div>")
                .append(content)
                .dialog($.extend({
                    title: title || locale.confirm,
                    autoOpen: true,
                    close: function(){
                        $(this).dialog("destroy");
                    },
                    buttons: [
                        {
                            text: locale.no,
                            click: function() {
                                $(this).dialog("close");
                                dfd.reject(locale.no);
                            },
                        },
                        {
                            text: locale.yes,
                            click: function() {
                                $(this).dialog("close");
                                dfd.resolve(locale.yes);
                            }
                        }
                    ]
                }, options));

        return $.extend(dialog, dfd.promise, {
            yes: function(cb){
                dfd.done(cb);
                return this;
            },
            no: function(cb){
                dfd.fail(cb);
                return this;
            }
        });
    };

    $.xui.dialogWizard = function(options){
        var defaults = {
            autoOpen: false,
            steps: 3,
            locale: "en-US"
        };
        options = $.extend({}, defaults, options);

        var callbacks = {},
            $this = $(this),
            currentStep = 1,
            buttons = {},
            indicators = $this.find(".steps li"),
            steps = $this.find(".step").hide(),
            locale = $.xui.dialogWizard.locale[options.locale];

        function addCallback(name){
            return function(cb){
                callbacks[name] = cb;
                return this;
            };
        }

        var methods = {
            finish: addCallback('finish'),
            cancel: addCallback('cancel'),
            next: function(){
                currentStep++;
                if (currentStep >= steps.length) {
                    buttons.next.hide();
                    buttons.finish.show();
                } else {
                    buttons.previous.button("enable");
                }
                indicators
                    .removeClass("current")
                    .eq(currentStep-2)
                    .addClass("finished")
                    .end()
                    .eq(currentStep-1)
                    .addClass("current");
                steps.hide().eq(currentStep-1).show();
            },
            previous: function(){
                currentStep--;
                if (currentStep === 1) {
                    buttons.previous.button("disable");
                } else {
                    buttons.next.show();
                    buttons.finish.hide();
                }
                indicators
                    .removeClass("current")
                    .eq(currentStep)
                    .removeClass("finished")
                    .end()
                    .eq(currentStep-1)
                    .addClass("current");
                steps.hide().eq(currentStep-1).show();
            }
        };

        for (var i=1; i<=steps.length; i++) {
            methods['step'+i] = addCallback('step'+i);
        }

        var dialog = $this
            .addClass("ui-dialog-wizard")
            .dialog($.extend({
                open: function(){
                    var $this = $(this);
                    currentStep = 1;
                    steps.hide().eq(0).show();
                    indicators
                        .removeClass("finished current")
                        .eq(0).addClass("current");
                    buttons.previous.button("disable");
                    buttons.next.show();
                    buttons.finish.hide();
                },
                buttons: [
                    {
                        text: locale.cancel || "Cancel",
                        "class": "wizard-cancel",
                        click: function() {
                            var cb = callbacks.cancel || false;
                            if (!cb || cb.apply($this[0])) {
                                $(this).dialog("close");
                            }
                        }
                    },
                    {
                        text: locale.previous || "Previous",
                        "class": "wizard-previous",
                        click: function() {
                            var cb = callbacks['step'+(currentStep-1)] || false;
                            if (!cb || cb.apply($this[0],[true])) {
                                methods.previous();
                            }
                        }
                    },
                    {
                        text: locale.next || "Next",
                        "class": "wizard-next",
                        click: function() {
                            var cb = callbacks['step'+(currentStep)] || false;
                            if (!cb || cb.apply($this[0])) {
                                methods.next();
                            }
                        }
                    },
                    {
                        text: locale.finish || "Finish",
                        "class": "wizard-finish",
                        click: function() {
                            var cb = callbacks.finish || false;
                            if (!cb || cb.apply($this[0])) {
                                $(this).dialog("close");
                            }
                        }
                    }
                ]
            }, options));

        $this.parent().find(".ui-button").each(function(){
            var $this = $(this);
            if ($this.hasClass("wizard-cancel")) {
                buttons.cancel = $this;
            } else if ($this.hasClass("wizard-previous")) {
                buttons.previous = $this;
            } else if ($this.hasClass("wizard-next")) {
                buttons.next = $this;
            } else if ($this.hasClass("wizard-finish")) {
                buttons.finish = $this;
            }
        });

        return $.extend(dialog, methods);
    };

    // Localization
    $.xui.dialogWizard.locale = {
        "en-US": {
            next: "Next",
            previous: "Previous",
            cancel: "Cancel",
            finish: "Finish"
        },
        "sv-SE": {
            next: "Nästa",
            previous: "Föregående",
            cancel: "Avbryt",
            finish: "Slutför"
        }
    };

    // Attach to jQuery fn
    $.fn.dialogWizard = $.xui.dialogWizard;

})(jQuery);

$(function() {
    // purely eyecandy
    $("button").button();

    // let's bind the alert button
    $("button.alert").click(function() {
        $.xui.alert("Hello World", "Alert");
    });

    // let's bind the confirm button
    $("button.confirm").click(function() {
        // let's create the confirm dialog
        var dialog = $.xui.confirm("<p>Do you really agree?</p>");

        // now let's decide what to do when the user actually answers
        // since we're using a deferreds promise here, it's easy!
        dialog
            .yes(function(){
                // User answered yes
                $("p.answer").text("Answer: YES! :)");
            })
            .no(function(){
                // User nswered yes
                $("p.answer").text("Answer: NO! :(");
            });



        // let's add a countdown to the dialog
        var countdown = 10;
        dialog.append("<p>You have <span>"+countdown+"</span> seconds to answer</p>");
        setInterval(function(){
            countdown--;
            if (countdown == 0) {
                clearInterval(this);
                dialog.dialog("close");
            }
            dialog.find("p:last span").text(countdown);
        }, 1000);

        // let's fool the user! *evil grin*
        setTimeout(function(){
            dialog.find("p:first").text("Do you really not agree?");
        }, 2000);
    });

    // wizardry
    $("#wizard")
        .dialogWizard({
            height: 300,
            width: 500
        })
        .cancel(function(){
            console.log("dialog was canceled");
            return true;
        })
        .step1(function(back){
            if (back) {
                console.log("moved back to step 1");
            } else {
                console.log("finished step 1");
            }
            return true;
        })
        .step2(function(back){
            if (back) {
                console.log("moved back to step 2");
            } else {
                console.log("finished step 2");
            }
            return true;
        })
        .step3(function(back){
            if (back) {
                console.log("moved back to step 3");
            } else {
                console.log("finished step 3");
            }
            return true;
        })
        .finish(function(){
            console.log("finished all!");
            return true;
        });


    $("button.wizard").click(function(){
        $("#wizard").dialog("open");
    });

});
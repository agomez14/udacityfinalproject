/*!CK:315500487!*//*1438197740,*/

if (self.CavalryLogger) { CavalryLogger.start_js(["a+AiL"]); }

__d("VideoQualityControl.react",["AbstractButton.react","Image.react","React","ReactComponentWithPureRenderMixin","cx","fbt","ix"],function(a,b,c,d,e,f,g,h,i,j,k,l,m){b.__markCompiled&&b.__markCompiled();'use strict';var n=i,o=n.PropTypes,p=i.createClass({displayName:"QualityControl",mixins:[j],propTypes:{isHD:o.bool,onToggleHD:o.func,tabIndex:o.string},render:function(){var q=((!this.props.isHD?"_2bs_":'')),r=(i.createElement(h,{className:q,src:m('images/video/player/controls/hq_icons/hd.png')}));return (i.createElement(g,{"aria-label":l._("Toggle HD"),tabIndex:this.props.tabIndex,type:"button",className:"_zbd",image:r,onClick:this.props.onToggleHD}));}});e.exports=p;},null);
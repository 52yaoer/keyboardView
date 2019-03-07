//
//  UZModuleDemo.m
//  UZModule
//
//  Created by kenny on 14-3-5.
//  Copyright (c) 2014年 APICloud. All rights reserved.
//

#import "KeyboardViewModule.h"
#import "UZAppDelegate.h"
#import "NSDictionaryUtils.h"
#import "KeyDataBean.h"
#import "SoftKeyBoardListener.h"
#import "UZAppUtils.h"

@interface KeyboardViewModule ()
<SoftKeyBoardChangeDelegate>
{
    NSInteger _keyboardViewCbId;
    NSInteger _mHeight;
    NSString *_mBackgroundColor;
    NSString *_mLineColor;
}
@property (nonatomic, strong) NSMutableArray *dataList;
@property (nonatomic, strong) UIView *keyBoardView;
@end

@implementation KeyboardViewModule

- (id)initWithUZWebView:(UZWebView *)webView_ {
    if (self = [super initWithUZWebView:webView_]) {
        _dataList  = [NSMutableArray array];
    }
    return self;
}

- (void)dispose {
    //do clean
    [_dataList removeAllObjects];
    [SoftKeyBoardListener obtain].delegate = nil;
    [[SoftKeyBoardListener obtain] setListener:NO];
    if(_keyBoardView){
        [_keyBoardView removeFromSuperview];
        _keyBoardView = nil;
    }
    _keyboardViewCbId = -1;
}

- (void)addKeyboardListener:(NSDictionary *)paramDict {
    _keyboardViewCbId = [paramDict integerValueForKey:@"cbId" defaultValue:0];
    _mHeight = [paramDict integerValueForKey:@"height" defaultValue:40];
    _mBackgroundColor = [paramDict stringValueForKey:@"backgroundColor" defaultValue:nil];
    _mLineColor = [paramDict stringValueForKey:@"lineColor" defaultValue:nil];
    NSArray *data = [paramDict arrayValueForKey:@"data" defaultValue:nil];
    if(data==nil){
        [self errPublic:_keyboardViewCbId msg:@"data is null"];
        return;
    }
    
    if(_keyBoardView){
        [_keyBoardView removeFromSuperview];
        _keyBoardView = nil;
    }
    if([_dataList count]>0){
        [[NSNotificationCenter defaultCenter] removeObserver:self];
    }
    
    [_dataList removeAllObjects];
    NSInteger index = 0;
    
    for (NSDictionary *keyValues in data) {
        NSString *xh = [keyValues stringValueForKey:@"id" defaultValue:@""];
        NSString *icon = [keyValues stringValueForKey:@"icon" defaultValue:nil];

        KeyDataBean *bean = [[KeyDataBean alloc] init];
        bean.index = index;
        bean.idx = xh;
        bean.icon = [self getPathWithUZSchemeURL:icon];
        [_dataList addObject:bean];
        index++;
    }
    
    [SoftKeyBoardListener obtain].delegate = self;
    [[SoftKeyBoardListener obtain] setListener:YES];
    
    [self succPublic:_keyboardViewCbId evenType:@"init"];
}

- (void)keyBoardShow:(int)height{
    if(_keyboardViewCbId<=0){
        return;
    }
    
    [UIView animateWithDuration:0 delay:1.0 options:UIViewAnimationOptionAllowUserInteraction animations:^{
    } completion:^(BOOL finished) {
        CGRect bounds = [[UIScreen mainScreen] bounds];
        int y = bounds.size.height-_mHeight - height;
        if([self statusBarAppearance]){
            // y += 20;
        }
        CGRect defaultRect = CGRectMake(0, y, bounds.size.width, _mHeight);
        
        if(_keyBoardView){
            [_keyBoardView setFrame:defaultRect];
            [self.viewController.view addSubview:_keyBoardView];
        }else{
            _keyBoardView = [[UIView alloc] initWithFrame:defaultRect];
            if(_mBackgroundColor.length<=0 || ![UZAppUtils isValidColor:_mBackgroundColor]){
                [_keyBoardView setBackgroundColor:[UIColor whiteColor]];
            }else{
                [_keyBoardView setBackgroundColor:[UZAppUtils colorFromNSString:_mBackgroundColor]];
            }
            [self.viewController.view addSubview:_keyBoardView];
            
            UIView *line = [[UIView alloc] initWithFrame:CGRectMake(0, 0, bounds.size.width, 1)];
            if(_mLineColor.length<=0 || ![UZAppUtils isValidColor:_mLineColor]){
                [line setBackgroundColor:[UZAppUtils colorFromNSString:@"#F8F8FF"]];
            }else{
                [line setBackgroundColor:[UZAppUtils colorFromNSString:_mLineColor]];
            }
            [_keyBoardView addSubview:line];
            
            
            NSUInteger cols = [_dataList count];
            
            CGFloat appvieww = _keyBoardView.frame.size.width/cols;
            CGFloat appviewh = _keyBoardView.frame.size.height * 2.7/3;
            
            for(NSInteger j = 0; j < cols ; j++){
                NSInteger loc = j % cols;
                CGFloat appviewx = appvieww * loc;
                
                UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(OnTapBtnView:)];
                
                KeyDataBean *bean = [_dataList objectAtIndex:j];
                
                UIView *appview = [[UIView alloc] initWithFrame:CGRectMake(appviewx, (_keyBoardView.frame.size.height-appviewh)/2, appvieww, appviewh)];
                [_keyBoardView addSubview:appview];
                
                UIImage *icon = [UIImage imageNamed:bean.icon];
                if(icon==nil){
                    NSDictionary *infoPlist = [[NSBundle mainBundle] infoDictionary];
                    NSString *icon1 = [[infoPlist valueForKeyPath:@"CFBundleIcons.CFBundlePrimaryIcon.CFBundleIconFiles"] lastObject];
                    icon = [UIImage imageNamed:icon1];
                }
                
                UIImageView *view = [[UIImageView alloc] initWithImage:icon];
                view.backgroundColor =[UIColor clearColor];
                
                NSInteger imageH = appview.frame.size.height;
                
                view.frame = CGRectMake((appview.frame.size.width - imageH)/2, 0, imageH, imageH);
                view.tag = bean.index;
                view.userInteractionEnabled = YES;
                [view addGestureRecognizer:tap];
                
                [appview addSubview:view];
            }
        }
        
        [self succPublic:_keyboardViewCbId evenType:@"show"];
    }];
}

-(void)OnTapBtnView:(UITapGestureRecognizer *)sender{
    KeyDataBean *bean = [_dataList objectAtIndex:sender.view.tag];
    if(bean.idx.length<=0){
        [self succPublic:_keyboardViewCbId evenType:@"click" key:@"id" value:[NSNumber numberWithInteger:bean.index]];
    }else{
        [self succPublic:_keyboardViewCbId evenType:@"click" key:@"id" value:bean.idx];
    }
}

- (void)keyBoardHide:(int)height{
    if(_keyboardViewCbId<=0){
        return;
    }
    [UIView animateWithDuration:0 delay:1.0 options:UIViewAnimationOptionAllowUserInteraction animations:^{
    } completion:^(BOOL finished) {
        if(_keyBoardView){
            [_keyBoardView removeFromSuperview];
            [self succPublic:_keyboardViewCbId evenType:@"hide"];
        }
    }];
}

- (void)removeKeyboardListener:(NSDictionary *)paramDict {
    NSInteger cbId = [paramDict integerValueForKey:@"cbId" defaultValue:0];
    if(!_keyBoardView && _keyboardViewCbId<=0){
        [self errPublic:cbId msg:@"addKeyboardListener not init"];
        return;
    }
    if(_keyBoardView){
        [_keyBoardView removeFromSuperview];
        _keyBoardView = nil;
    }
    _keyboardViewCbId = -1;
    [self succPublic:cbId];
}

-(void)succPublic:(NSInteger)cbId{
    [self succPublic:cbId status:YES];
}

-(void)succPublic:(NSInteger)cbId status:(BOOL)status{
    if(cbId>0){
        NSDictionary *ret = [NSDictionary dictionaryWithObject:[NSNumber numberWithBool:status] forKey:@"status"];
        [self sendResultEventWithCallbackId:cbId dataDict:ret errDict:nil doDelete:NO];
    }
}

-(void)succPublic:(NSInteger)cbId key:(NSString*)key value:(id)value{
    if(cbId>0){
        NSMutableDictionary *ret = [NSMutableDictionary dictionaryWithObject:[NSNumber numberWithBool:YES] forKey:@"status"];
        if(key.length>0){
            [ret setObject:value==nil?@"":value forKey:key];
        }
        [self sendResultEventWithCallbackId:cbId dataDict:ret errDict:nil doDelete:NO];
    }
}

-(void)succPublic:(NSInteger)cbId evenType:(NSString *)evenType{
    [self succPublic:cbId evenType:evenType key:nil value:nil];
}

-(void)succPublic:(NSInteger)cbId evenType:(NSString *)evenType key:(NSString*)key value:(id)value{
    if(cbId>0){
        NSMutableDictionary *ret = [NSMutableDictionary dictionaryWithObject:[NSNumber numberWithBool:YES] forKey:@"status"];
        [ret setObject:evenType forKey:@"evenType"];
        if(key.length>0){
            [ret setObject:value==nil?@"":value forKey:key];
        }
        [self sendResultEventWithCallbackId:cbId dataDict:ret errDict:nil doDelete:NO];
    }
}

-(void)errPublic:(NSInteger)cbId msg:(NSString *)msg{
    if(cbId>0){
        NSDictionary *ret = [NSDictionary dictionaryWithObject:[NSNumber numberWithBool:NO] forKey:@"status"];
        NSMutableDictionary * errdict = [NSMutableDictionary dictionaryWithObject:msg forKey:@"msg"];
        [self sendResultEventWithCallbackId:cbId dataDict:ret errDict:errdict doDelete:NO];
    }
}

@end
